var app = angular.module('app', []);

app.controller('TodoController', function($http) {
	var self = this;
	self.todo = {};
	self.todos = [];
	
	$http.get('/api/todos')
	    .then(function(success) {
	    	self.todos = success.data;
	    }, function(error) {
	    	console.error(error);
	    });
	
	self.saveTodo = function() {
		var data = {
		    message: self.todo.message
		};
		
		$http.post('/api/todos', data)
		    .then(function(success) {
		    	self.todos.push(success.data);
		    }, function(error) {
		    	console.error(error);
		    });
		
	};
});