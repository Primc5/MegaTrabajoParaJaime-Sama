<?php
/*  Formato JSON esperado */
$arrEsperado = array();
$arrEmpresaEsperado = array();
$arrEmpresaEsperado["id_Empresa"] = "1";
$arrEsperado["peticion"] = "delete";
$arrEsperado["EmpresaBorrar"] = $arrEmpresaEsperado;
/* Funcion para comprobar si el recibido es igual al esperado */
function JSONCorrectoBorrarEmpresa($recibido){
	$auxCorrecto = false;
	if(isset($recibido["peticion"]) && $recibido["peticion"] ="delete" && isset($recibido["EmpresaBorrar"])){
		$auxActor = $recibido["EmpresaBorrar"];
		if(isset($auxActor["id_Empresa"])){
			$auxCorrecto = true;
		}
	}
	return $auxCorrecto;
}
