// text.js - modulo encargado de recibir un mensaje de texto comun ya validado y
// generar la operacion correspondiente indicada en el mensaje, eso implica generar el log
// y la sentencia de base de datos correspondiente. (crear).

exports.getType = function() {
    return 'texto';
}