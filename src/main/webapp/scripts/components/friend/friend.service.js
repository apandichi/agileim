'use strict';

angular.module('agileimApp')
    .factory('Friend', function ($resource) {
        return $resource('api/friends/:id', {id: '@id'}, {
                'query': {method: 'GET', isArray: true},
                'get': {
                    method: 'GET',
                    transformResponse: function (data) {
                        data = angular.fromJson(data);
                        return data;
                    }
                },
                'markAsFriend': { method:'PUT' }
            });
        });
