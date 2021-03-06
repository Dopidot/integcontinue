var app = angular.module('app', [])
              .config(['$qProvider', function ($qProvider) {
                  $qProvider.errorOnUnhandledRejections(false);
               }]);

app.controller('TodoController', function($http) {
	var self = this;
	self.todo = {};
	self.todos = [];

	self.doNotMatch = null;
	self.error = null;
	self.success = null;

	$http.get('/api/todos')
	    .then(function(success) {
	    	self.todos = success.data;
	    }, function(error) {
	    	console.error(error);
	    });

	self.saveTodo = function() {

		if(self.todo.message == null) {
			self.doNotMatch = 'ERROR';
			return;
		}

		var data = {
		    message: self.todo.message
		};

		$http.post('/api/todos', data)
		    .then(function(success) {
		    	self.success = 'OK';
		    	self.todos.push(success.data);
		    }, function(error) {
		    	self.error = 'ERROR';
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

	self.saveModification = function(id, newMessage) {

		if(id == null || newMessage == null) {
			return;
		}

		var data = {
			id: id,
		    message: newMessage
		};

		$http.put('/api/todos', data)
		    .then(function(success) {
		    	self.todos.forEach(function(item, index){
		    		if(item.id == id) {
		    			item.message = newMessage;
		    			return;
		    		}
		    	}, this);
		    }, function(error) {
		    	console.error(error);
		    });
	};

});