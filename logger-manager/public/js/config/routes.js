// routes
(function () {
	'use strict';
	angular.module('MyRoutes', ['ngRoute'])
        .config(['$routeProvider', function ($routeProvider) {

            $routeProvider
                .when('/', {
                    templateUrl: 'views/navigation/home.html',
					title:'Inicio'
                })
				.when('/resume', {
                    templateUrl: 'views/logs/resume.html',
					controller: 'resumeCtrl',
					title:'Resumen de Logs'
                })
				.when('/message', {
					templateUrl: 'views/message/list.html',
					controller: 'messageCtrl',
					title:'Mensajes'
				})
				.when('/mobile/:id', {
					templateUrl: 'views/mobile/view.html',
					controller: 'mobileViewCtrl',
					title:'Mensajes del dispositivo'
				})
				.when('/message/:id/:type', {
					templateUrl: 'views/message/view.html',
					controller: 'messageViewCtrl',
					title:'Traza del mensaje'
				})
				.when('/messages', {
					templateUrl: 'views/message/list.html',
					controller: 'messageListCtrl',
					title:'Listado de mensajes'
				})
				.when('/logs', {
					templateUrl: 'views/logs/list.html',
					controller: 'logListCtrl',
					title:'Listado de logs'
				})
                /* Default */
                .otherwise({
                    redirectTo: '/'
                });
            }]);
})();
