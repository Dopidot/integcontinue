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
		
		if(self.todo.message == null) {
			return;
		}
		
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
	
	self.deleteTodo = function(id) {
		
		$http.delete('/api/todos/' + id)
		    .then(function(success) {
		    	
		    	self.todos.forEach(function(item, index){
		    		if(item.id == id) {
		    			self.todos.splice(index, 1);
		    			return;
		    		}
		    	}, this);
		    	
		    }, function(error) {
		    	console.error(error);
		    });
		
	};
	
});