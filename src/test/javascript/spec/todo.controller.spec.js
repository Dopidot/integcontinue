'use strict';

describe('Controller Tests', function() {
    beforeEach(mockApiPOSTTodoCall);
    beforeEach(mockApiGETTodoCall);

    describe('TodoController', function() {

        var $scope, $httpBackend, $q;
        var MockTodo;
        var createController;

        beforeEach(inject(function($injector) {
            $scope = $injector.get('$rootScope').$new();
            $q = $injector.get('$q');
            $httpBackend = $injector.get('$httpBackend');

            MockTodo = jasmine.createSpyObj('MockTodo', ['saveTodo']);
            var locals = {
                '$scope': $scope,
            };
            createController = function() {
                $injector.get('$controller')('TodoController as vm', locals);
            }
        }));

        it('should show error if field do not match', function() {
            //GIVEN
            createController();
            $scope.vm.todo = {};
            $scope.vm.todos = [];
            //WHEN
            $scope.vm.saveTodo();
            //THEN
            expect($scope.vm.doNotMatch).toBe('ERROR');
            expect($scope.vm.error).toBeNull();
            expect($scope.vm.success).toBeNull();
        });
    });
});
