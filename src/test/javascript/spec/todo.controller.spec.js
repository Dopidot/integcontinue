describe('TodoController', function() {
    var controller;
    beforeEach(function() {
      module('app');
      inject(function($injector) {
        $http = $injector.get('$http');
        controller = $injector.get('$controller')('TodoController', {$http: $http});
      });
    });

    describe('Initialization of todos', function() {
      it('Should check if todos exist', function() {
        expect(controller.todos.length).toBe(0);
      });
    });

    describe('Initialization of todo', function() {
      it('Should check if todo exist', function() {
        expect(controller.todo).not.toBe(null);
      });
    });
});