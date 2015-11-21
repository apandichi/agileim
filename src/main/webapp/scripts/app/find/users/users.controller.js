'use strict';

angular.module('agileimApp')
    .controller('UsersController', function ($scope, User) {
        User.query(function(result) {
        	console.log("users result" + result);
        	$scope.users = result;
        });
        
        $scope.markAsFriend = function(userId) {
        	console.log("marked user id " + userId);
        };
    });
