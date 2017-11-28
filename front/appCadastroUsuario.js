appRequesicoes.controller('usuariosCadastroController', function($scope, $http,$q){
	$scope.usuario = {};
	$scope.usuarios = [];

    $scope.capturaUsuario = function(){
		$scope.usuario = JSON.parse(window.sessionStorage.getItem('usuario'));
        console.log($scope.usuarios);
        window.sessionStorage.removeItem('usuario');
	}

	$scope.salvar = function(){
		return $scope.usuario.idUsuario == null ? $scope.salvarUsuario() : $scope.editarUsuario();
	}

	$scope.salvarUsuario = function(){
		$http({
			method: 'POST',
			url: 'http://localhost:8080/usuario',
			data: $scope.usuario,
			headers: {
				'Content-Type': 'application/json',
				'Access-Control-Allow-Origin' : '*',
				'Access-Control-Allow-Methods' : 'GET, POST, PUT, DELETE',
				'X-Requested-With': 'XMLHttpRequest',
				'Access-Control-Allow-Headers' : 'Origin, X-Requested-With, Content-Type, Accept'
			}

		}).then(function (response) {
			alert("Usuario Salvo!");
			$scope.usuario = {};
			console.log(response.status);
		}, function (response) {
			$scope.usuario = {};
			console.log(response.data);
			console.log(response.status); 
		});
	}

	$scope.editarUsuario= function(){
		$http({
			method: 'PUT',
			url: 'http://localhost:8080/usuario',
			data: $scope.usuario,
			headers: {
				'Content-Type': 'application/json',
				'Access-Control-Allow-Origin' : '*',
				'Access-Control-Allow-Methods' : 'GET, POST, PUT, DELETE',
				'X-Requested-With': 'XMLHttpRequest',
				'Access-Control-Allow-Headers' : 'Origin, X-Requested-With, Content-Type, Accept'
			}
		}).then(function (response) {
			alert("Usuario Salvo!");
			$scope.usuario = {};
			console.log(response.status);
		}, function (response) {

			$scope.usuario = {};
			console.log(response.data);
			console.log(response.status); 
		});
	}

	$scope.cancelarUsuario = function(){
		$scope.usuario = {};
	}

	$scope.capturaUsuario();

});