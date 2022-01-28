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
    };

    $scope.clearAll = function () {
        $http({
            url: contextPath + '/students',
            method: 'DELETE',
        }).then(function () {
            $scope.showed = false;
            $scope.fillTable();
        })
    };

    $scope.play = function () {
        $http({
            url: contextPath + '/students/play',
            method: 'GET',
        }).then(function (response) {
            $scope.StudentOne = response.data[0];
            $scope.StudentTwo = response.data[1];
            $scope.showed = response.data != "";
            $scope.fillTable();
        })
    };

    $scope.plus = function (id) {
        $http({
            url: contextPath + '/students/plus/' + id,
            method: 'POST',
        }).then(function () {
            $scope.fillTable();
        })
    };

    $scope.minus = function (id) {
        $http({
            url: contextPath + '/students/minus/' + id,
            method: 'POST',
        }).then(function () {
            $scope.fillTable();
        })
    };

    $scope.import = function (path){
        $http({
            url: contextPath + '/students/import',
            method: 'POST',
            data: path
        }).then(function () {
            $scope.fillTable();
        })
    }

    $scope.fillTable();
});