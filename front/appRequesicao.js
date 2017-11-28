appRequesicoes.controller('cadastroRequesicaoController', function($scope, $http,$q){

	$scope.requesicao = {};
	$scope.usuarios = [];


    $scope.capturaRequesicao = function(){
		$scope.requesicao = JSON.parse(window.sessionStorage.getItem('requesicao'));
        console.log($scope.requesicao);
		window.sessionStorage.removeItem('requesicao');
		if($scope.requesicao == null){
			 $scope.carregarUsuarios();
		}else{
			console.log("ENtrou aqui");
			$scope.usuarios = [{
				'idUsuario': 'room0',
				'nome': '10'
			}];
		}

	}


	$scope.salvar = function(){
		return $scope.requesicao.idRequesicao == null ? $scope.salvarRequesicao() : $scope.editarRequesicao();
	}


	/* ***** REQUESIÇÕES ***** */

	$scope.carregarUsuarios = function(){
		$http({
			method: 'GET',
			url: 'http://localhost:8080/usuario'

		}).then(function (response) {
			$scope.usuarios = response.data;
			console.log(response.status);
		}, function (response) {
			console.log(response.data);
			console.log(response.status); 
		});
    }
    

    $scope.salvarRequesicao = function(){

		console.log("entrou");
		console.log($scope.requesicao);

		$http({
			method: 'POST',
			url: 'http://localhost:8080/requesicao',
			data: $scope.requesicao,
			headers: {
				'Content-Type': 'application/json',
				'Access-Control-Allow-Origin' : '*',
				'Access-Control-Allow-Methods' : 'GET, POST, PUT, DELETE',
				'X-Requested-With': 'XMLHttpRequest',
				'Access-Control-Allow-Headers' : 'Origin, X-Requested-With, Content-Type, Accept'
			}
		}).then(function (response) {
            alert("Requesição Salva!");
            $scope.requesicao = {};
			console.log(response.status);
		}, function (response) {
			alert(response.data.message+". Impossivel salvar");
			console.log(response.status); 
		});
	}

	$scope.editarRequesicao = function(){
		console.log("Editar");
		$http({
			method: 'PUT',
			url: 'http://localhost:8080/requesicao',
			data: $scope.requesicao,
			headers: {
				'Content-Type': 'application/json',
				'Access-Control-Allow-Origin' : '*',
				'Access-Control-Allow-Methods' : 'GET, POST, PUT, DELETE',
				'X-Requested-With': 'XMLHttpRequest',
				'Access-Control-Allow-Headers' : 'Origin, X-Requested-With, Content-Type, Accept'
			}
		}).then(function (response) {
            alert("Requesição Salva!");
            $scope.requesicao = {};
			console.log(response.status);
		}, function (response) {
			alert(response.data.message+". Impossivel salvar");
			console.log(response.data);
			console.log(response.status); 
		});
	}
    
    $scope.cancelarAlteracaoRequesicao = function(requesicao){
		$scope.requesicao = {};
	}

	$scope.carregarUsuarios();
	$scope.capturaRequesicao();
});