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

    $scope.play = function () {
        $http({
            url: contextPath + '/students/play',
            method: 'GET',
        }).then(function (response) {
            if (response.data == ""){
                $scope.result = "Все ответили!"
                $scope.studfirst = "";
                $scope.studsecond = "";
            }
            $scope.studfirst = "Вопрос задает: " + response.data[0].firstname + " " + response.data[0].secondname;
            $scope.studsecond = "Отвечает на вопрос: "+ response.data[1].firstname + " " + response.data[1].secondname;
            $scope.fillTable();
        })
    }

    $scope.fillTable();
});