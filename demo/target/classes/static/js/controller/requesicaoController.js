appRequesicoes.controller('requesicoesController', function($scope, $http,$q){

	$scope.requisicoes  = [];
	$scope.requesicao ={};

	//Carregar todas as requesicao
	$scope.carregarRequesicoes = function(){
		$http({
			method: 'GET',
			url: 'http://localhost:8080/listarRequesicao'
		}).then(function (response) {
			$scope.requisicoes = response.data;
			console.log(response.status);
		}, function (response) {
			console.log(response.data);
			console.log(response.status); 
		});	
	}
	
	//Buscar apenas uma requesicao
	$scope.buscarRequesicao = function(idRequesicao){
		$http({
			method: 'GET',
			url: 'http://localhost:8080/buscarRequesicao/'+idRequesicao
		}).then(function (response) {
			console.log(response.data);
			console.log(response.status);
		}, function (response) {
			console.log(response.data);
			console.log(response.status); 
		});	
	}
	
	//Buscar o cliente
	$scope.buscarRequesicaoCliente = function(idCliente){
		$http({
			method: 'GET',
			url: 'http://localhost:8080/buscaRequesicaoCliente/'+idCliente
		}).then(function (response) {
			console.log(response.data);
			console.log(response.status);
		}, function (response) {
			console.log(response.data);
			console.log(response.status); 
		});	
	}

	//Salvar
	$scope.salvarRequesicao = function(){
		$http({
			method: 'POST',
			url: 'http://localhost:8080/salvarRequesicao',
			data: $scope.requesicao

		}).then(function (response) {
			$scope.carregarRequesicoes();
			$scope.requesicao = {};
			console.log(response.status);
		}, function (response) {
			console.log(response.data);
			console.log(response.status); 
		});
	}

	//Deletar
	$scope.deletarRequesicao = function(requesicao){
		console.log(requesicao.idRequesicao);
		$http({
			method: 'POST',
			url: 'http://localhost:8080/deletarRequesicao/'+requesicao.idRequesicao
		}).then(function (response) {
			$scope.carregarRequesicoes();
			console.log(response.status);
		}, function (response) {
			console.log(response.data);
			console.log(response.status); 
		});
	}

	//Alterar
	$scope.alterarRequesicao = function(requesicao){
		$scope.requesicao = requesicao;
	}
	//Cancelar
	$scope.cancelarAlteracaoRequesicao = function(requesicao){
		$scope.requesicao = {};
	}

	$scope.carregarRequesicoes();

});
