var Api = function () {

    //pre default options
    var options = {
        url: "",
        actions:{
            list: {url: "/list", type: "GET"},
            add:  {url: "/add", type: "PUT"},
            remove: {url: "/delete", type: "PUT"},
            update: {url: "/update", type: "POST"}
        },
        requestParam: {
            async:true,
            dataType: "json",
            type: "post"
        }
    };

    var Api = function(url,opt){

        var self = this;

        this.options = {};
        this.options = _.extend(options, opt);
        if(arguments.length > 1) _.extend(self.options, opt);

        this.url = url;
        this.actionsUrl = self.options.actionsUrl;
        this.requestParam = self.options.requestParam;

        this.actions = {};
        _.each(_.allKeys(options.actions),function(name){
            self.actions[name] = function (data,success,error) {
                call(self.actions.url,data,success,error,{type:self.actions.type});
            }
        });
    };

    Api.prototype = {
        call: function(url,data,success,error,requestParam){

            var params = {};
            var result = null;
            _.extend(params,options.requestParams);
            if(arguments.length > 4) _.extend(params,requestParams);

            //Set specific data of request
            params.url = this.url + url;
            params.data = data;
            params.success = success;
            params.error = error;
        }
    }




};
