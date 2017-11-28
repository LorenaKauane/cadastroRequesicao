appRequesicoes.controller('clientesController', function($scope, $http,$q){
	$scope.nome = "Nome do cliente";

	$scope.cliente = {};
	$scope.clientes = [];

	$scope.carregarClientes = function(){
		$http({
			method: 'GET',
			url: 'http://localhost:8080/listarClientes'
		}).then(function (response) {
			$scope.clientes = response.data;
			console.log(response.status);
		}, function (response) {
			console.log(response.data);
			console.log(response.status); 
		});	

	}

	$scope.salvarCliente = function(){
		$http({
			method: 'POST',
			url: 'http://localhost:8080/salvarCliente',
			data: $scope.cliente

		}).then(function (response) {
			$scope.carregarClientes();
			$scope.cliente = {};
			console.log(response.status);
		}, function (response) {
			$scope.carregarClientes();
			$scope.cliente = {};
			console.log(response.data);
			console.log(response.status); 
		});
		// $http
 	//         .post("http://localhost:8080/salvarRequesicao", $scope.requesicao)
	//         .then(res => $q.resolve(res.data), error => $q.reject(error))

	}


	$scope.deletarCliente = function(cliente){
		console.log(cliente.idCliente);
		$http({
			method: 'POST',
			url: 'http://localhost:8080/deletarCliente/'+cliente.idCliente
		}).then(function (response) {
			$scope.carregarClientes();
			console.log(response.status);
		}, function (response) {
			console.log(response.data);
			console.log(response.status); 
		});
	}



	$scope.alterarCliente = function(cliente){
		$scope.cliente = cliente;
	}

	$scope.cancelarCliente = function(){
		$scope.cliente = {};
	}


	$scope.carregarClientes();


});