

var Application = (function(opt){


    var PhonebookEntryController = function(opt){
        
        var options = {
            url: "/phonebook/entry/",
            tableId: "phonebook-entry-table",
            fieldSufix: "field"
        };
        this.options = _.extend(options, opt);

        this.api = new Api(this.options.url);

        this.table = new EntryTable(this.options.tableId,this.options.fieldSufix);

        this.selectedEntry = null;
        
        

    };

    PhonebookEntryController.prototype = {
        list: function(filter){
            
            
            
            this.api.actions.list(filter, function (data) {
                
            });
        },
        add: function(){

        },
        update: function(){

        },
        remove: function(){

        },
        search: function() {

        }

    }
    
    var EntryTable = function(id,prefix){
        
    }
    
    EntryTable.prototype = {
        selectEntry : function(entry){
            
        },
        deselectEntry : function(){
            
        },
        appendEntry: function(entry){
            
        },
        deleteEntry: function(entry){
            
        },
        updateEntry: function (entry) {
            
        }
    }



})();
