angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8190/api/v1';

    $scope.fillTable = function () {
        $http({
            url: contextPath + '/students',
            method: 'GET'
        }).then(function (response) {
            $scope.StudentsPage = response.data;
        });
    };

    $scope.addNewStudents = function (text) {
        $http({
            url: contextPath + '/students',
            method: 'POST',
            data: text
        }).then(function () {
            $scope.fillTable();
        })
    }

    $scope.fillTable();
});