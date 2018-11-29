<?php

/*  Formato JSON esperado */

$arrEsperado = array();
$arrJuegoEsperado = array();

$arrEsperado["peticion"] = "add";

$arrJuegoEsperado["nombre"] = "El ultimo de nosotros (Un string)";
$arrJuegoEsperado["tipo"] = "tres As (Un int)";
$arrJuegoEsperado["Empresa"] = "perro (Un string)";
$arrJuegoEsperado["Creacion"] = "2013-06-14 (Una date)";

$arrEsperado["juegoAnnadir"] = $arrJuegoEsperado;


/* Funcion para comprobar si el recibido es igual al esperado */

function JSONCorrectoAnnadir($recibido){

	$auxCorrecto = false;

	if(isset($recibido["peticion"]) && $recibido["peticion"] ="add" && isset($recibido["juegoAnnadir"])){

		$auxJuego = $recibido["juegoAnnadir"];
		if(isset($auxJuego["nombre"]) && isset($auxJuego["tipo"]) && isset($auxJuego["Empresa"])
	 && isset($auxJuego["Creacion"]))
	 {
			$auxCorrecto = true;
		}

	}


	return $auxCorrecto;

}
