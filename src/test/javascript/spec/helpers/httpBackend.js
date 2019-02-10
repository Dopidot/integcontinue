function mockApiPOSTTodoCall() {
    inject(function($httpBackend) {
        $httpBackend.whenPOST(/api\/todos.*/).respond({});
    });
}

function mockApiGETTodoCall() {
    inject(function($httpBackend) {
        $httpBackend.whenGET(/api\/todos.*/).respond({});
    });
}

function mockScriptsCalls() {
    inject(function($httpBackend) {
        $httpBackend.whenGET(/app\/.*/).respond({});
    });
}
