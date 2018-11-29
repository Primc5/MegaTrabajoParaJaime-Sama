<?php
/*  Formato JSON esperado */
$arrEsperado = array();
$arrJuegosEsperado = array();
$arrJuegosEsperado["Id"] = "1";
$arrEsperado["peticion"] = "delete";
$arrEsperado["juegoBorrar"] = $arrJuegosEsperado;
/* Funcion para comprobar si el recibido es igual al esperado */
function JSONCorrectoBorrarJuego($recibido){
	$auxCorrecto = false;
	if(isset($recibido["peticion"]) && $recibido["peticion"] ="delete" && isset($recibido["juegoBorrar"])){
		$auxActor = $recibido["juegoBorrar"];
		if(isset($auxActor["Id"])){
			$auxCorrecto = true;
		}
	}
	return $auxCorrecto;
}
