
appRequesicoes.controller('usuariosController', function($scope, $http,$q){

	$scope.usuario = {};
	$scope.usuarios = [];
	$scope.usuarioBusca = null;

	$scope.confirmaDeletar= function(requesicao){
        if(confirm('Deseja realmente excluir esse usuario?'))$scope.deletarUsuario(requesicao);
    }

	/* ***** Requesições ***** */
	$scope.carregarUsuarios = function(){
		$http({
			method: 'GET',
			url: 'http://localhost:8080/usuario'
		}).then(function (response) {
			$scope.usuarios = response.data;
			console.log(response.data);
			console.log(response.status);
		}, function (response) {
			console.log(response.data);
			console.log(response.status); 
		});	

	}

	$scope.buscarUsuario= function(){
		console.log($scope.usuarioBusca);
		$http({
			method: 'GET',
			url: 'http://localhost:8080/usuario?search=START nome:'+$scope.usuarioBusca+' ORcpf:'+$scope.usuarioBusca+' ORemail:'+$scope.usuarioBusca+' ORidUsuario:'+$scope.usuarioBusca
		}).then(function (response) {
			console.log(response.data);
			console.log(response.status);
			if(response.data == 0){
				alert("Não foi encontrado esse usuario");
				$scope.carregarUsuarios();
				$scope.carregarRequesicoes();
			}else{
				$scope.usuarios = response.data;
			}
		}, function (response) {
			
			console.log(response.data);
			console.log(response.status); 
		});	
	}

	$scope.deletarUsuario = function(Usuario){
		console.log(Usuario.idUsuario);
		$http({
			method: 'DELETE',
			url: 'http://localhost:8080/usuario/'+Usuario.idUsuario
		}).then(function (response) {
			$scope.carregarUsuarios();
			console.log(response.status);
		}, function (response) {
			alert(response.data.message);
			console.log(response.data);
			console.log(response.status); 
		});
	}

	$scope.alterarUsuario = function(usuario){
		window.sessionStorage.setItem('usuario', JSON.stringify(usuario));
		window.location.href='cadastroUsuario.html'
	}

	$scope.carregarUsuarios();
});