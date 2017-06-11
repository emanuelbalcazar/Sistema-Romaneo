// router.js - en este modulo se declaran las rutas manejadas por el logger-manager.
var express = require('express');
var router = express.Router();
var logController = require('../controllers/logController');

// Ruta principal de la aplicacion.
router.get('/', function(req, res) {
    res.send('aplicacion inicializada');
});

// Retorna la cantidad de registros de logs.
router.get('/api/logs/countAll', function(req, res) {
    logController.countAll(function(error, result) {
        res.jsonp(result);
    });
});

// Retorna un resumen agrupado de logs.
router.get('/api/resume', function(req, res) {
    logController.findResumen(function(error, result) {
        res.send(result);
    });
});

// Retorna los mensajes logeados de un dispositivo en particular.
router.get('/api/mobile/:imei', function(req, res) {
    logController.findMessagesByMobile(req.params.imei, function(error, result) {
        res.send(result);
    });
});

// router.get('/*', function(req, res) {
//     res.render('index.html');
// });

module.exports = router;
