var appRequesicoes = angular.module('appRequesicoes', []);

appRequesicoes.config(function ($httpProvider) {
	$httpProvider.defaults.headers.common = {};
	$httpProvider.defaults.headers.post = {};
	$httpProvider.defaults.headers.put = {};
	$httpProvider.defaults.headers.patch = {};
  });


appRequesicoes.controller('requesicoesController', function($scope, $http,$q){

	$scope.requisicoes  = [];
	$scope.requesicao ={};
	$scope.requesicaoBusca = null;
	$scope.requesicaoBuscaUsuario = null;

	$scope.usuario = [];

	$scope.confirmaDeletar= function(requesicao){
        if(confirm('Deseja realmente excluir está Requesição?')){
            $scope.deletarRequesicao(requesicao);
        }
    }


	/* ***** Requesições ***** */
	$scope.carregarRequesicoes = function(){
		$http({
			method: 'GET',
			url: 'http://localhost:8080/requesicao'
		}).then(function (response) {
			$scope.requisicoes = response.data;
			console.log(response.status);
		}, function (response) {
			console.log(response.data);
			console.log(response.status); 
		});	
	}
	
	$scope.buscarRequesicao = function(){
		console.log($scope.requesicaoBusca);

		$http({
			method: 'GET',
			url: 'http://localhost:8080/requesicao?search=START tipoRequesicao:'+$scope.requesicaoBusca+' ORidRequesicao:'+$scope.requesicaoBusca+' ORobservacaoRequesicao:'+$scope.requesicaoBusca+' ORdata:'+$scope.requesicaoBusca
		}).then(function (response) {
			console.log(response.data);
			console.log(response.status);
			if(response.data == 0){
				alert("Não foi encontrado essa Requesição");
				$scope.carregarRequesicoes();
			}else{
				$scope.requisicoes = response.data;
			}
		}, function (response) {
			
			console.log(response.data);
			console.log(response.status); 
		});	
	}

	$scope.deletarRequesicao = function(requesicao){
		console.log(requesicao.idRequesicao);
		$http({
			method: 'DELETE',
			url: 'http://localhost:8080/requesicao/'+requesicao.idRequesicao
		}).then(function (response) {
			$scope.carregarRequesicoes();
			alert("Requesição Deletada!");
			console.log(response.status);
		}, function (response) {
			$scope.carregarRequesicoes();
			console.log(response.data);
			console.log(response.status); 
		});
	}

	$scope.alterarRequesicao = function(requesicao){
		window.sessionStorage.setItem('requesicao', JSON.stringify(requesicao));
		window.location.href='cadastroRequesicao.html'
	}

	$scope.carregarRequesicoes();

});


