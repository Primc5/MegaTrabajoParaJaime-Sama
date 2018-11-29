<?php

/*  Formato JSON esperado */

$arrEsperado = array();
$arrEmpresaEsperado = array();

$arrEsperado["peticion"] = "add";

$arrEmpresaEsperado["Nombre"] = "Bugisos (Un string)";
$arrEmpresaEsperado["Tamanno"] = "tres Ases (Un int)";
$arrEmpresaEsperado["Pais"] = "espanita (Un string)";
$arrEmpresaEsperado["Capital"] = "mucho dinero (Una int)";
$arrEmpresaEsperado["Director"] = "somos un utopia no necesitamos un lider, porque somos nuestros propios lideres (Una string)";

$arrEsperado["empresaAnnadir"] = $arrEmpresaEsperado;


/* Funcion para comprobar si el recibido es igual al esperado */

function JSONCorrectoAnnadir($recibido){

	$auxCorrecto = false;

	if(isset($recibido["peticion"]) && $recibido["peticion"] ="add" && isset($recibido["juegoAnnadir"])){

		$auxEmpresa = $recibido["empresaAnnadir"];
		if(isset($auxEmpresa["Nombre"]) && isset($auxEmpresa["Tamanno"]) && isset($auxEmpresa["Pais"])
	 && isset($auxEmpresa["Capital"]) && isset($auxEmpresa["Director"]))
	 {
			$auxCorrecto = true;
		}

	}


	return $auxCorrecto;

}
