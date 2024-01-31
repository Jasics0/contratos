DELIMITER //

CREATE TRIGGER before_insert_update_contrato
BEFORE INSERT ON iteria.contrato
FOR EACH ROW
BEGIN
  DECLARE contrato_existente INT;
  DECLARE plan_estado INT;

  -- Obtener el estado del plan asociado al contrato
  SELECT pln_estado
  INTO plan_estado
  FROM iteria.plan
  WHERE plnId = NEW.pln_id;

  -- Verificar si ya existe un contrato activo con el mismo afiliado y plan
  SELECT COUNT(*)
  INTO contrato_existente
  FROM iteria.contrato
  WHERE afi_id = NEW.afi_id
    AND pln_id = NEW.pln_id
    AND ctoFechaRetiro > CURRENT_DATE;  -- Contratos activos son aquellos con fecha de retiro después de la fecha actual

  -- Si ya existe un contrato activo, impedir la inserción
  IF contrato_existente > 0 THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'No se puede insertar el contrato. El afiliado ya tiene un contrato activo con el mismo plan.';
  END IF;

  -- Verificar si la fecha de retiro del contrato nuevo es igual a la fecha de registro del contrato nuevo
  IF NEW.ctoFechaRetiro IS NOT NULL AND NEW.ctoFechaRetiro = NEW.ctoFechaRegistro THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'No se puede insertar el contrato. La fecha de retiro coincide con el día de registro del contrato.';
  END IF;

  -- Verificar que el plan asociado al contrato tenga estado 1
  IF plan_estado <> 1 THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'No se puede insertar el contrato. El plan asociado al contrato no está activo.';
  END IF;
END;

//
DELIMITER ;

DELIMITER //

CREATE TRIGGER after_update_afiliado
AFTER UPDATE ON iteria.afiliado
FOR EACH ROW
BEGIN
  -- Verificar si el estado del afiliado ha cambiado a 0
  IF NEW.afiEstado = 0 AND OLD.afiEstado <> NEW.afiEstado THEN
    -- Obtener los contratos asociados al afiliado que aún no han sido retirados
    UPDATE iteria.contrato
    SET ctoFechaRetiro = CURRENT_DATE
    WHERE afi_id = NEW.afiId AND ctoFechaRetiro IS NULL;
  END IF;
END;

//
DELIMITER ;
