
angular.module("mainApp" , ["ngResource","angularjs-datetime-picker","textAngular","keren.core.calendar","keren.core.discussion","keren.core.commons"]);        

//Creation du controleur
angular.module("mainApp")
.controller("mainCtrl" , function($scope ,$rootScope,$http,$location,$filter, $compile,$timeout,commonsTools,restService){
    $scope.desablecreate = false;    
    $scope.desableupdate = false;
    $scope.desabledelete = false;
    $scope.desableprint = false;
    $scope.desablecreateedit = false;
    $scope.desableupdateedit = false;
    $scope.desabledeleteedit = false;
    $scope.desableprintedit = false;
    $scope.showpjmenu = false;
   //Pour module Calendrier
     $scope.showCalendar = false;
    //Hide the discussion
    $scope.showDiscussion = false;
    //Hide the discussion
    $scope.hideOther = false;
    //Label Exporter 
    $scope.exportbtnlabel = 'Exporter'; 
    //Label Update 
    $scope.updatebtnlabel = 'Modifier'; 
    //Label Exporter 
    $scope.deletebtnlabel = 'Supprimer'; 
    //Module courant = application
    $scope.showApplication = false;
   //Hide Discussion login , calendar ,discussion ,others
   $scope.moduleValue = "login";
   //Number de messages
   $scope.numberofnewmessages = 0;
   //List of available users and canal
   $scope.tchatinput = new Array();        
   //Utilisateur courant
   $scope.applicationsModule = { id:-1 , name:"discussionconf",label:"Discussion",selected:false,hasmenu:false,
                          groups:[
                                                      
                          ]

               }
   /**
       Structure du module de configuration
   **/
   $scope.configurationModule = { id:-1 , name:"configuration",label:"Configuration",selected:false,hasmenu:true,
                 groups:[
                      {id:-1 , name:"utilisateurs",label:"Utilisateurs",icon:"glyphicon glyphicon-user" ,showmenu:true,
                       actions:[
                          {id:-1,name:"utilisateur" , label:"Utilisateurs",icon:"glyphicon glyphicon-user",entityName:"Utilisateur",moduleName:"kerencore",modal:false,securitylevel:0,model:'kerencore'},
                          {id:-2,name:"groupes" , label:"Groupes",icon:"glyphicon glyphicon-list-alt",entityName:"Groupe",moduleName:"kerencore",modal:false,securitylevel:0,model:'kerencore'},
                          {id:-3,name:"societe" , label:"Societes",icon:"glyphicon glyphicon-home",entityName:"Societe",moduleName:"kerencore",modal:false,securitylevel:0,model:'kerencore'},
                          {id:-4,name:"pays" , label:"Pays",icon:"glyphicon glyphicon-flag",entityName:"pays",moduleName:"kerencore",modal:false,securitylevel:0,model:'kerencore'}
                       ]},
                       { id:-2 , name:"traduction" , label:"Traductions",icon:"glyphicon glyphicon-book" ,showmenu:true,
                         actions:[{id:-1,name:"langues" , label:"Langues",icon:"glyphicon glyphicon-bullhorn",entityName:"Langue",moduleName:"kerencore",modal:false,securitylevel:0,model:'kerencore'},
                                  {id:-2,name:"chargelangue" , label:"Charger une traduction",icon:"glyphicon glyphicon-saved",entityName:"ChargerLangue",moduleName:"kerencore",modal:true,securitylevel:0,model:'kerencore'}
                                  //,{id:3,name:"importtraduction" , label:"Importer une traduction",icon:"glyphicon glyphicon-user",entityName:null,moduleName:"kerencore",modal:false},
                                  //{id:4,name:"exportertraduction" , label:"Exporter une traduction",icon:"glyphicon glyphicon-user",entityName:null,moduleName:"kerencore",modal:false}
                         ]},
                         {id:-4 , name:"discussion_conf",label:"Discussion",icon:"glyphicon glyphicon-th",showmenu:true,
                                actions:[
                                   {id:-100,name:"show_canal_discussion" , label:"Canaux de communication",icon:"glyphicon glyphicon-th",entityName:"Canal",moduleName:"kerencore",modal:false,securitylevel:0,model:'kerencore'}
//                                   {id:-2,name:"application_update" , label:"Mise a jour",icon:"glyphicon glyphicon-refresh",entityName:"MenuModule",moduleName:"kerencore",modal:false,securitylevel:0,model:'kerencore'}                          
                           ]},
                       {id:-3 , name:"techniques",label:"Technique",icon:"glyphicon glyphicon-cog", showmenu:true,
                       actions:[
                          {id:-1,name:"actionsdb" , label:"Actions",icon:"glyphicon glyphicon-th",entityName:"ActionItem",moduleName:"kerencore",modal:false,securitylevel:2,model:'kerencore'},
                          {id:-2,name:"menu" , label:"Elements de Menu",icon:"glyphicon glyphicon-th",entityName:"MenuGroupActions",moduleName:"kerencore",modal:false,securitylevel:0,model:'kerencore'},
                          {id:-3,name:"actions" , label:"Actions Menu",icon:"glyphicon glyphicon-align-center",entityName:"MenuAction",moduleName:"kerencore",modal:false,securitylevel:0,model:'kerencore'},
                          {id:-4,name:"formview" , label:"Vues Formulaire",icon:"glyphicon glyphicon-list-alt",entityName:"FormRecord",moduleName:"kerencore",modal:false,securitylevel:0,model:'kerencore'},
                          {id:-5,name:"treeview" , label:"Vues Arborescente",icon:"glyphicon glyphicon-align-justify",entityName:"TreeRecord",moduleName:"kerencore",modal:false,securitylevel:0,model:'kerencore'},
                          {id:-6,name:"calendarview" , label:"Vues Calendrier",icon:"glyphicon glyphicon-align-justify",entityName:"CalendarRecord",moduleName:"kerencore",modal:false,securitylevel:0,model:'kerencore'},
                          {id:-7,name:"dashboardview" , label:"Tableaux de bord",icon:"glyphicon glyphicon-align-justify",entityName:"DashboardRecord",moduleName:"kerencore",modal:false,securitylevel:0,model:'kerencore'},
                          {id:-8,name:"reportview" , label:"Editique",icon:"glyphicon glyphicon-align-justify",entityName:"ReportRecord",moduleName:"kerencore",modal:false,securitylevel:0,model:'kerencore'}
                       ]}
                 ]

      };
     
   /**
       Structure du module de configuration
   **/
   $scope.applicationsModule = { id:-1 , name:"application",label:"Applications",selected:false,hasmenu:true,
                 groups:[
                      {id:-1 , name:"application",label:"Applications",icon:"glyphicon glyphicon-th",showmenu:true,
                       actions:[
                          {id:-1,name:"applications" , label:"Applications",icon:"glyphicon glyphicon-th-list",entityName:"MenuModule",moduleName:"kerencore",modal:false,securitylevel:0,model:'kerencore'},
                          {id:-2,name:"application_update" , label:"Mise a jour",icon:"glyphicon glyphicon-refresh",entityName:"MenuModule",moduleName:"kerencore",modal:false,securitylevel:0,model:'kerencore'}                          
                       ]}                       
                 ]

      };
   
   
   $scope.metaData = {  entityName:"Module",
                         name:{type:"string" , search:true ,fieldName:"name" , "fieldLabel":"Nom Module"},
                         shortDescription:{type:"string" , search:true,fieldName:"shortDescription" , fieldLabel:"Description Module"},
                         actions:{type:"array" , search:false,fieldName:"actions"
                         ,metaData:{name:{type:"string" , search:true,fieldName:"name"},
                                    shortDescription:{type:"string" , search:true,fieldName:"description"}
                                   }
                               }
                      };

   $scope.checkbox01 = false;
    /**
       type de fenetre active : new ,update , view ,list
    **/
    $scope.windowType = 'new';
    
    $scope.messageType = "inner";
    $scope.innerWindowType = 'new';
    
    $scope.currentModule = null;

    $scope.currentObject = null;

    $scope.enabledVerticalMenu = false;

    $scope.tableheaderselected = false;

    $scope.dataSelects = [] ;

     
     /**
      * 
      * @returns {Boolean}
      */
     $scope.hideApplicationModule = function(){
         if(!$rootScope.globals.user){
             return true;
         }
         if($rootScope.globals.user.adminlevel==0||$rootScope.globals.user.adminlevel==2){
             return true;
         }else{
             return false;
         }
     };
     
     /**
      * 
      * @returns {Boolean}
      */
     $scope.hideConfiguration = function(){
         if(!$rootScope.globals.user){
             return true;
         }
         if($rootScope.globals.user.adminlevel==0||$rootScope.globals.user.adminlevel==1){
             return true;
         }else{
             return false;
         }
     };
     /**
      * Chargement des modules de l'utilisateur
      * @returns {undefined}
      */
     $scope.getModulesForCurrentUser = function(){
         commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%");
                //Chargement des modules de l'utilisateur
                restService.url('utilisateur','kerencore');
                restService.findByStringProperty('courriel',$rootScope.globals.currentUser.username).$promise
                        .then(function(data){
                            if(data.length>0){
                                $rootScope.globals.user = data[0];
                                $rootScope.globals.company = $rootScope.globals.user.societeCourante;
                                $http.defaults.headers.common['user']= angular.toJson(data[0]);           
                                //Chargement  des modules
                                var url = 'http://'+$location.host()+':'+$location.port()+'/kerencore/utilisateur/application'
                                $http.get(url).then(
                                        function(response){
                                            $scope.modules = response.data;
                                            //console.log(angular.toJson($scope.modules));
                                            $scope.discussionModule();
                                            commonsTools.hideDialogLoading();
                                        },function(error){
                                             commonsTools.hideDialogLoading();
                                             commonsTools.showMessageDialog(error);
                                        });                       
                           }
                        },function(error){
                            commonsTools.hideDialogLoading();
                            commonsTools.showMessageDialog(error);
                        });
                        //Chargement du logo de l'application
                        //restService.downloadPNG("appli_icon_id","logo.png");
     };

    /** 
      Function de chargement du module 
      courant(module selectionné)
    **/
    $scope.getModule = function(module){      
    	if(angular.isDefined(module)){    		
             $scope.currentModule = module;
             $scope.enabledVerticalMenu = false;
             $scope.moduleValue = "others";
             //Notification du changement du module
             $rootScope.$broadcast("currentModule" , {module:$scope.currentModule , verticalMenu:$scope.enabledVerticalMenu});
             if(angular.isDefined(module.groups) && (module.groups.length > 0)){
                //Chargement de l'action par defaut
                $scope.enabledVerticalMenu = true;
                //$scope.getSelectAction(module.groups[0].actions[0]);           
                $scope.getSelectAction($scope.currentModule.groups[0].actions[0]);             
            }//end if(angular.isDefined(module.groups) && (module.groups.length > 0))             
              //Hide the calendar panel
               $scope.showCalendar = false ;
               //Hide the discussion
                $scope.showDiscussion = false;
                //Hide General
                $scope.hideOther = $scope.showCalendar || $scope.showDiscussion ;    
                //Chargement du logo de l'application
                restService.downloadPNG("logo.png","appli_icon_id");
             
    	}//end if(angular.isDefined(module))
    };
    /**  
       Fonction de traitement de l'action
       courant()
    **/
    $scope.getSelectAction = function(action){
    	$scope.currentAction = null;	
    	if(angular.isDefined(action)){
    		$scope.currentAction = action;
                $rootScope.$broadcast("currentActionUpdate" ,{
                                   action:$scope.currentAction , verticalMenu:$scope.enabledVerticalMenu});  
    	}    	
    };

    /**
     * 
     * @returns {undefined}
     */
    $scope.getDefaultModule = function(){         
        $scope.getModulesForCurrentUser();
    };

    /**
    Action devant appeler l'entite
    **/
    $scope.executeActionEvent = function(action){

    };

/**
 * 
 * @returns {undefined}
 */
    $scope.onCheckboxClick = function(){
        
           	 $scope.tableheaderselected = !$scope.tableheaderselected;
         
	     	 for(var i=0 ; i<$scope.modules.length;i++){
	     	    module = $scope.modules[i];	     	 	 		  
	     	 	module.selected = $scope.tableheaderselected;
	     	 }
                 
             for(var i=0 ; i<$scope.modules.length;i++){
             	  module = $scope.modules[i];	     
             	  //console.log("Vous avez cliquez sur "+module.name+" === "+module.shortDescription+"==="+module.selected); 
             }  
    };


     /**
       Fonction de gestion
    **/
    $scope.configurationModuleAction = function(){
      $scope.enabledVerticalMenu = false;
      $scope.moduleValue = "others";
      $scope.currentModule = $scope.configurationModule;
      //Notification du changement du module
      $rootScope.$broadcast("currentModule" , {module:$scope.currentModule , verticalMenu:$scope.enabledVerticalMenu});
      if(angular.isDefined($scope.currentModule.groups) && ($scope.currentModule.groups.length > 0)){
            $scope.enabledVerticalMenu = true;
            //var module = $scope.currentModule;
            $scope.getSelectAction($scope.currentModule.groups[0].actions[0]);
      }//end if(angular.isDefined($scope.currentModule.groups) && ($scope.currentModule.groups.length > 0))       
        //Hide the calendar panel
        $scope.showCalendar = false ;
        //Hide the discussion
        $scope.showDiscussion = false;
         //Hide application action
        $scope.showApplication = true ;
        //$scope.getModule($scope.configurationModule);    
        $scope.hideOther = $scope.showCalendar || $scope.showDiscussion ;
        //Chargement du logo de l'application
        restService.downloadPNG("logo.png","appli_icon_id");
    };

     /**
        Gestion du module applications
     **/
    $scope.applicationModule = function(){
         $scope.enabledVerticalMenu = true;
        $scope.moduleValue = "applications";
        $scope.currentModule = $scope.applicationsModule;
        //Notification du changement du module
        $rootScope.$broadcast("currentModule" , {module:$scope.currentModule , verticalMenu:$scope.enabledVerticalMenu});
        if(angular.isDefined($scope.currentModule.groups) && ($scope.currentModule.groups.length > 0)){
                $scope.enabledVerticalMenu = true;
                //var module = $scope.currentModule;
             $scope.getSelectAction($scope.currentModule.groups[0].actions[0]);
          }
        //Hide the calendar panel
        $scope.showCalendar = false ;
        //Hide the discussion
        $scope.showDiscussion = false;
        $scope.moduleValue = "others";
        $scope.hideOther = $scope.showCalendar || $scope.showDiscussion ;
         //console.log("Vous avez cliquer sur le module ::: configuration");
         //Chargement du logo de l'application
        restService.downloadPNG("logo.png","appli_icon_id");
    };

    /**
      Module des Discussion
    **/
    $scope.discussionModule = function(){
         
        //Hide the calendar panel
        $scope.showCalendar = false ;
        //Hide the discussion
        $scope.showDiscussion = true;
        $scope.moduleValue = "discussion";
        //Desactivi
        $scope.enabledVerticalMenu = false;
        $scope.hideOther = $scope.showCalendar || $scope.showDiscussion ;
        //Chargement du logo de l'application
        restService.downloadPNG($rootScope.globals.user.image,"mail_user_id");        
        //console.log("Vous avez cliquer sur le module ::: Discussion");
        $rootScope.$broadcast("discussionmodule" , {verticalMenu:$scope.enabledVerticalMenu});
    };
    
   
    /**
       Module des calendriers
    **/
    $scope.calendrierModule = function(){
        commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%");
        //Hide the calendar panel
        $scope.showCalendar = true ;
        //Hide the discussion
        $scope.showDiscussion = false;
        //Hide the verticam Menu
        $scope.enabledVerticalMenu = false;
        $scope.hideOther = $scope.showCalendar || $scope.showDiscussion ;
        $scope.moduleValue = "calendar";
        //Initialisation du service Rest
        restService.url('event','kerencore');
        restService.getMetaData(null).$promise.then(
                        function(metaData){
                             $scope.metaData = metaData;
                             var url = "http://"+$location.host()+":"+$location.port()+"/kerencore/event/event/"+$rootScope.globals.userinfo.id; 
                             $http.get(url).then(
                                /**
                                 * 
                                 * @param {type} datas
                                 * @returns {undefined}
                                 */
                                    function(response){
                                         //Notification du changement du module
                                         $rootScope.$broadcast("calendarModule" , {metaData:metaData , events:response.data});
                                         commonsTools.hideDialogLoading();
                                    },
                                    function(error){
                                         commonsTools.hideDialogLoading();
                                         commonsTools.showMessageDialog(error);
                                    }
                                 );
                           
                        },
                /**
                 * Cas d'erret
                 * @returns {undefined}
                 */
                        function(error){
                            commonsTools.hideDialogLoading();
                            commonsTools.showMessageDialog(error);
                        });
        
        //console.log("Vous avez cliquer sur le module ::: Calendrier");
    };
    
    /**
     * Deconnexion de l'applicatiopn
     * @returns {undefined}
     */
    $scope.deconnexion = function(){
          //$scope.hideDialogLoading();                                                        
        $rootScope.$broadcast("login" , {  });  
    };
    /**
     * 
     * @returns {undefined}
     */
    $scope.tchat = function(){
              $scope.currentUser = $rootScope.globals.userinfo;
               $scope.tchatinput = new Array();
//                 console.log(angular.toJson($scope.currentUser));
                var url = "http://"+$location.host()+":"+$location.port()+"/kerencore/canal/canaux/"+$scope.currentUser.courriel;
                commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%");
                $http.get(url)
                        .then(function(response){
                             var result = response.data;
                             for(var i=0;i<result.length;i++){
                                $scope.tchatinput.push(result[i]);
                             }//end for(var i=0;i<result.length;i++){
                             var url = "http://"+$location.host()+":"+$location.port()+"/kerencore/canal/directe/"+$scope.currentUser.courriel;
//                commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%");
                            $http.get(url)
                                    .then(function(response){
                                        var result = response.data;
                                        for(var i=0;i<result.length;i++){
                                            $scope.tchatinput.push(result[i]);
                                        }//end for(var i=0;i<result.length;i++){
                                        //console.log("$scope.tchat ====================== "+angular.toJson($scope.tchatinput));
                                        $scope.tchatinput = $filter('orderBy')($scope.tchatinput,'designation',false);    
                                        commonsTools.hideDialogLoading();
                                    },function(error){
                                        commonsTools.hideDialogLoading();
                                        commonsTools.showDialogLoading(error);
                                    });
                        },function(error){
                            commonsTools.hideDialogLoading();
                            commonsTools.showDialogLoading(error);
                        });
//        generateTchat();
    };
    /**
     * 
     * @param {type} item
     * @returns {undefined}
     */
    $scope.tchatwindow = function(item){
        var zoneid = "zone"+item.id
    };
    
    /**
     * 
     */
    $scope.updatePassword = function(){
        var action = {id:-80,name:"modifpassword" , label:"Elements de Menu",icon:"glyphicon glyphicon-th",entityName:"PwdUser2",moduleName:"kerencore",modal:true,securitylevel:0,model:'kerencore'};
        $rootScope.$broadcast("currentActionUpdate" ,{
                                   action:action , verticalMenu:$scope.enabledVerticalMenu});  
    };
    /**
     * Chargement du module par defaut
     */
    $scope.getDefaultModule();

    //Hide Discussion login , calendar ,discussion ,others
    //Chargement des modules de l'utilisateur connecté;
//    $scope.$on("user_modules" ,function(event ,args){
//        console.log("Felicitation vous ête authentifié == "+args.modules);
//    });
   /**
            * Reception des evenement de d'edition des etats
            */
          $scope.$on("updatemessagesnumber" , function(event, args){
//               console.log("customreport =========== "+angular.toJson(args.metaData)); 
               $scope.numberofnewmessages = args.value;
          });

});

angular.module("mainApp")
.directive("headerBuilder" , function(restService,commonsTools,$rootScope ,$filter,$http , $compile , $parse , $timeout,$location){
    
    return {
    	restrict : "E",
        scope : {    		    
            enabledVerticalMenu: "@fullScreen",
            currentAction:"@selectAction"
             
            
    	},
      controller: function($scope , $element , $attrs){
           
           

           $scope.currentObject = new Object();
           
           //Contient la liste du contenue des lignes selectionnées
          $scope.selectedObjects = [];

          $scope.temporalDatas = [];
          
          //Champ temporaire de stockage des données
          $scope.temporalData = {};
          
          //Model temporaire
          $scope.temporalModel = null;
          //Cache applicatif
          $scope.dataCache = {};
          //Cache de resource
          $scope.resourcesCache = {};
          //Show or enable report title
          $scope.showreporttitle = false;
          //Données courantes
          $scope.datas = [];
          //Liste des predicats
          $scope.predicats = new Array();
          $scope.searchCriteria = new String();
          $scope.suffixedittitle = 'Nouveau';
          $scope.enablefollowerpanel = false;
          $scope.activefollower = false;
          //information pour construire le calendrier
          $scope.calendarrecord = null;
          $scope.viewmode = "tree";
           //Calandar event source
          $scope.eventSources = [];
          $scope.events = [
//                    {title: 'All Day Event',start: new Date(y, m, 1),selected:false},
//                    {title: 'Long Event',start: new Date(y, m, d - 5),end: new Date(y, m, d - 2),selected:false},
//                    {id: 999,title: 'Repeating Event',start: new Date(y, m, d - 3, 16, 0),allDay: false,selected:false},
//                    {id: 999,title: 'Repeating Event',start: new Date(y, m, d + 4, 16, 0),allDay: false,selected:false},
//                    {title: 'Birthday Party',start: new Date(y, m, d + 1, 19, 0),end: new Date(y, m, d + 1, 22, 30),allDay: false,selected:false},
//                    {title: 'Click for Google',start: new Date(y, m, 28),end: new Date(y, m, 29),url: 'http://google.com/',selected:false}
                ];
                var isFirstTime = true ;
          //Calendar section
          //Configuration du calendrier
                $scope.uiConfig ={
                    calendar:{
                        height:480,
                        editable:true,
                        displayEventTime:false,
                        header:{
                            left:'prev,today,next,month,agendaWeek,agendaDay',
                            center:'title',
                            right:' '
                        },
                        selectable:true,
                        selectHelper:true,
                        select:function(start,end){     
//                              console.log("calendarCong !!!!!!!!!!!!!!!!!! "+(new Date(start)));
                              $scope.currentObject = commonsTools.createEmptyObject($scope.metaData);
                              $scope.currentObject.start =new Date(start);;
                              $scope.currentObject.end = new Date(start); ;
                              if($scope.calendarrecord!=null){
                                  if($scope.calendarrecord.startfield){
                                      $scope.currentObject[$scope.calendarrecord.startfield]= new Date(start);
                                  }
                                  $scope.currentObject['allDay']=$scope.calendarrecord.allday;
                                  if($scope.calendarrecord.endfield){
                                       $scope.currentObject[$scope.calendarrecord.endfield]=new Date(end);
                                  }
                              }//end if($scope.calendarrecord!=null)                              
                              $scope.previousType="calendar";
                               $scope.addNewCalendar();
//                              $('.selectpicker').selectpicker('refresh');
                             //console.log("Vous avez cliquez sur selectionner");
                        },
                        eventClick:function(event){
//                            $scope.currentObject=event;
                            $scope.previousType = 'calendar';//Notification du changement du module
                             $scope.viewAction(event);                    
                        },
                        eventAfterAllRender:function(){
                            if($scope.events.length>0 && isFirstTime){
                                //Focus first event
                            //      console.log("=============== "+angular.toJson(uiCalendarConfig));
                                  if(uiCalendarConfig.calendars && uiCalendarConfig.calendars.myCalendar){
                                    uiCalendarConfig.calendars.myCalendar.fullCalendar('gotoDate',$scope.events[0].start);
                                  }
                                  isFirstTime = false;
                           }
                        }
                    }
                };
          $scope.pagination = {  
                                 beginIndex: 0,
                                 endIndex : 0,
                                 currentPage :0,
                                 pageSize : 15 ,
                                 totalPages : 0,
                                 enabledNext: true,
                                 enabledPrevious:true,
                                 /** Get the next pages **/
                                 next:function(){
                                     //Notification du chargement
                                     //Verification si on peut avance
                                     if(this.hasnext()){
                                         $scope.showDialogLoading("Chargement ...","white","#9370db","0%","0%");  
                                         //this.currentPage = (this.currentPage*this.pageSize)%this.totalPages; 
                                         $scope.pagination.beginIndex = $scope.pagination.endIndex+1;                                        
                                         //Chargement des donnes
                                          restService.filter($scope.predicats ,$scope.pagination.beginIndex , $scope.pagination.pageSize)
                                                .$promise.then(function(data){
//                                                      console.log('$scope.loadData = function() :::::::::::::::: '+data);
                                                     if(data){
                                                         $scope.datas = data;
                                                         //Traitement des données
                                                        if($scope.calendarrecord){
                                                             for(var i=0;i<$scope.datas.length;i++){
                                                                var data = $scope.datas[i];
                                                                if($scope.calendarrecord.titlefield){
                                                                    data['title'] = data[$scope.calendarrecord.titlefield];
                                                                }
                                                                if($scope.calendarrecord.startfield){
                                                                    data['start'] = data[$scope.calendarrecord.startfield];
                                                                }
                                                                if($scope.calendarrecord.endfield){
                                                                    data['end'] = data[$scope.calendarrecord.endfield];
                                                                }
                                                                data['allDay'] = $scope.calendarrecord.allday;
                                                             }//end for(var i=0;i<datas.length;i++){
                                                             $scope.eventSources = [datas];
                                                         }//end if($scope.calendarrecord)
                                                         $scope.pagination.currentPage = $scope.pagination.beginIndex;
                                                         $scope.pagination.endIndex = $scope.pagination.endIndex+$scope.pagination.pageSize;
                                                         if($scope.pagination.endIndex>$scope.pagination.totalPages){
                                                             $scope.pagination.endIndex = $scope.pagination.totalPages;
                                                         }
                                                         //$scope.hideDialogLoading();
                                                         $rootScope.$broadcast("dataLoad" , {
                                                             message:"dataLoad"
                                                         });
                                                     }
                                                } ,function(error){
                                                    $scope.hideDialogLoading();
                                                    commonsTools.showMessageDialog(error);
                                                });  
                                     }//end if(this.hasnext()){
                                 },
                                 hasnext:function(){
                                     //Verifie that current pages is less or egal than totalPages
                                     if(this.endIndex>=this.totalPages){
                                         this.enabledNext = false ;                                        
                                     }else{
                                         this.enabledNext = true;
                                     }
                                     return this.enabledNext;
                                 },                                 
                                 hasNextPage:function(){
                                      if(this.currentPage>=this.totalPages){
                                         return false;
                                     }
                                     return true ;
                                 },
                                hasPreviousPage:function(){
                                     if(this.currentPage<=1){
                                       return false;
                                   }  
                                   return true;
                                 },            
                                 nextPage:function(){
                                     if(this.hasNextPage()){
                                         $scope.pagination.currentPage +=1;
                                         $rootScope.$broadcast("displayitem",{index:$scope.pagination.currentPage});
                                     }else{
                                          if(this.hasnext()){
                                            $scope.showDialogLoading("Chargement ...","white","#9370db","0%","0%");  
                                            //this.currentPage = (this.currentPage*this.pageSize)%this.totalPages; 
                                            $scope.pagination.beginIndex = $scope.pagination.endIndex+1;                                        
                                            //Chargement des donnes
                                             restService.filter($scope.predicats ,$scope.pagination.beginIndex , $scope.pagination.pageSize)
                                                   .$promise.then(function(data){
//                                                         console.log('$scope.pagination.nextPage: = function() :::::::::::::::: '+data);
                                                        if(data){
                                                            $scope.datas = data;
                                                             //Traitement des données
                                                            if($scope.calendarrecord){
                                                                 for(var i=0;i<$scope.datas.length;i++){
                                                                    var data = $scope.datas[i];
                                                                    if($scope.calendarrecord.titlefield){
                                                                        data['title'] = data[$scope.calendarrecord.titlefield];
                                                                    }
                                                                    if($scope.calendarrecord.startfield){
                                                                        data['start'] = data[$scope.calendarrecord.startfield];
                                                                    }
                                                                    if($scope.calendarrecord.endfield){
                                                                        data['end'] = data[$scope.calendarrecord.endfield];
                                                                    }
                                                                    data['allDay'] = $scope.calendarrecord.allday;
                                                                 }//end for(var i=0;i<datas.length;i++){
                                                                 $scope.eventSources = [datas];
                                                             }//end if($scope.calendarrecord)
                                                            $scope.pagination.currentPage = $scope.pagination.beginIndex;
                                                            $scope.pagination.endIndex = $scope.pagination.endIndex+$scope.pagination.pageSize;
                                                            if($scope.pagination.endIndex>$scope.pagination.totalPages){
                                                                $scope.pagination.endIndex = $scope.pagination.totalPages;
                                                            }
                                                            //$scope.hideDialogLoading();
                                                            $rootScope.$broadcast("displayitem",{index:$scope.pagination.currentPage});
                                                            $scope.hideDialogLoading(); 
                                                       }
                                                   } ,function(error){
                                                       $scope.hideDialogLoading();
                                                       commonsTools.showMessageDialog(error);
                                                   });  
                                        }//end if(this.hasnext()){
                                     }//end if(this.hasNextPage())
                                 },
                                 previousPage:function(){
                                     if(this.hasPreviousPage()){
                                          $scope.pagination.currentPage -=1;
                                         $rootScope.$broadcast("displayitem",{index:$scope.pagination.currentPage});
                                     }else{
                                         if(this.hasprevious()){
                                            $scope.showDialogLoading("Chargement ...","white","#9370db","0%","0%");  
                                            var interval = this.endIndex - this.beginIndex;
                                            this.beginIndex = this.beginIndex-this.pageSize-1;
                                            this.currentPage = this.beginIndex+1 ;
                                            if(this.beginIndex<0){
                                                this.beginIndex = 0;
                                                this.currentPage = 1;
                                            }
                                            if(interval>=this.pageSize){
                                                this.endIndex = this.endIndex - this.pageSize;
                                            }else{
                                                this.endIndex = this.endIndex - interval-1;
                                            }//end if(interval>=this.pageSize){
                                            if(this.endIndex<=0){
                                               this.endIndex = this.pageSize;                                                             
                                            }//end if(this.endIndex<=0)
                                             restService.filter($scope.predicats ,$scope.pagination.beginIndex , $scope.pagination.pageSize)
                                                   .$promise.then(function(data){
//                                                         console.log('$scope.pagination.previousPage: = function() :::::::::::::::: '+data);
                                                        if(data){
                                                            $scope.datas = data;                                                         
                                                            //$scope.hideDialogLoading();
                                                            $rootScope.$broadcast("displayitem",{index:$scope.pagination.currentPage});
                                                        }
                                                        $scope.hideDialogLoading();
                                                   } ,function(error){
                                                       $scope.hideDialogLoading();
                                                       commonsTools.showMessageDialog(error);
                                                   });  
                                        }//end if(this.hasprevious()){
                                     }//end if(this.hasPreviousPage())
                                 },
                                 hasprevious:function(){
                                     if(this.beginIndex<=0){
                                         this.enabledPrevious = false ;
                                     }else{
                                        this.enabledPrevious = true ;
                                    }
                                    return this.enabledPrevious;
                                 },
                                 previous:function(){
                                     if(this.hasprevious()){
                                         $scope.showDialogLoading("Chargement ...","white","#9370db","0%","0%");  
                                         var interval = this.endIndex - this.beginIndex;
                                         this.beginIndex = this.beginIndex-this.pageSize-1;
                                         this.currentPage = this.beginIndex+1 ;
                                         if(this.beginIndex<0){
                                             this.beginIndex = 0;
                                             this.currentPage = 1;
                                         }
                                         if(interval>=this.pageSize){
                                             this.endIndex = this.endIndex - this.pageSize;
                                         }else{
                                             this.endIndex = this.endIndex - interval-1;
                                         }//end if(interval>=this.pageSize){
                                         if(this.endIndex<=0){
                                            this.endIndex = this.pageSize;                                                             
                                         }//end if(this.endIndex<=0)
                                          restService.filter($scope.predicats ,$scope.pagination.beginIndex , $scope.pagination.pageSize)
                                                .$promise.then(function(data){
//                                                      console.log('$scope.loadData = function() :::::::::::::::: '+data);
                                                     if(data){
                                                         $scope.datas = data;                                                         
                                                         //$scope.hideDialogLoading();
                                                         $rootScope.$broadcast("dataLoad" , {
                                                             message:"dataLoad"
                                                         });
                                                     }
                                                } ,function(error){
                                                    $scope.hideDialogLoading();
                                                    commonsTools.showMessageDialog(error);
                                                });  
                                     }//end if(this.hasprevious()){
                                 }
                                 
                           };
            $scope.temporalPagination = {
                                 module:null ,
                                 model:null,
                /** Get the next pages **/
                                 next_2:function(){
                                     //Notification du chargement
                                     //Verification si on peut avance
                                     if(this.hasnext()){
                                         commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%");  
                                         //this.currentPage = (this.currentPage*this.pageSize)%this.totalPages; 
                                         this.beginIndex = this.endIndex+1;  
                                         var url = "http://"+$location.host()+":"+$location.port()+"/"+this.module+"/"+this.model+"/filter/"+this.beginIndex+"/"+this.pageSize;
                                         //Chargement des donnes
                                          $http.get(url)
                                                .then(function(response){
                                                    var datas = response.data;
                                                      //console.log('$scope.loadData = function() :::::::::::::::: '+datas);
                                                     if(datas){
                                                         $scope.dataCache[model] = datas;
                                                         this.currentPage = this.beginIndex;
                                                         this.endIndex = this.endIndex + pageSize;
                                                         if(this.endIndex>totalPages){
                                                             this.endIndex = this.totalPages;
                                                         }
                                                         
                                                     }
                                                     commonsTools.hideDialogLoading();
                                                } ,function(error){
                                                    commonsTools.hideDialogLoading();
                                                    commonsTools.showMessageDialog(error);
                                                });  
                                     }
                                 },                                 
                                 previous_2:function(){
                                     if(this.hasprevious()){
                                         commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%");  
                                         var interval = this.endIndex - this.beginIndex;
                                         this.beginIndex = this.beginIndex-this.pageSize-1;
                                         this.currentPage = this.beginIndex+1 ;
                                         if(this.beginIndex<0){
                                             this.beginIndex = 0;
                                             this.currentPage = 1;
                                         }
                                         if(interval>=this.pageSize){
                                             this.endIndex = this.endIndex - this.pageSize;
                                         }else{
                                             this.endIndex = this.endIndex - interval-1;
                                         }
                                         if(this.endIndex<=0){
                                            this.endIndex = this.pageSize;                                                             
                                         }//end if(this.endIndex<=0)
                                         var url = "http://"+$location.host()+":"+$location.port()+"/"+angular.lowercase(this.module)+"/"+angular.lowercase(this.model)+"/filter/"+this.beginIndex+"/"+this.pageSize;
                                          $http.get(url)
                                                  .then(function(response){
                                                      var datas = response.data;
                                                      //console.log('$scope.loadData = function() :::::::::::::::: '+datas);
                                                     if(datas){
                                                         $scope.dataCache[model] = datas;                                                   
                                                     }
                                                     commonsTools.hideDialogLoading();
                                                } ,function(error){
                                                    $scope.hideDialogLoading();
                                                    commonsTools.showMessageDialog(error);
                                                });  
                                     }
                                 }                
            };   
            //Heritage des data et met
           angular.extend($scope.temporalPagination,$scope.pagination);
           /**
              Type de la fenetre
              value : new , update , view , list
           **/
          $scope.windowType = 'new';
          /**
             Chemin d'acces au metaData
          **/
         $scope.currentMetaDataPath = 'metaData';
         $scope.temporalMetaData = null;
         
         
         /**
          * 
          * @returns {Boolean}Droite de creation 
          */
         $scope.canCreate = function(){
             if($scope.currentAction){
                 return ($scope.currentAction.securitylevel>=0)&&($scope.currentAction.securitylevel<=1);
             }  
             return false ;
         };
         
         $scope.canDelete = function(){
             if($scope.currentAction){
                 return ($scope.currentAction.securitylevel==0);
             }
             return false;
         };
         $scope.canUpdate = function(){
             if($scope.currentAction){
                 return ($scope.currentAction.securitylevel>=0)&&($scope.currentAction.securitylevel<=1);
             }  
             return false ;
         };
         $scope.canPrint = function(){
             if($scope.currentAction){
                 return ($scope.currentAction.securitylevel>=0)&&($scope.currentAction.securitylevel<=2);
             }  
             return false ;
         };
         
         $scope.showActions = function(){
             return $scope.selectedObjects.length>0 && $scope.showApplication==false;
         };
          /**

           **/
          $scope.showDialogLoading =function(texte, color, colorContent, topPos,leftPos) {
            $('body').append("<div id='dialogContent' style='width:100%;height:100%;position:absolute;z-index:2000;text-align:center;'></div>");
            $('#dialogContent').append("<div id='dialogWindow'></div>");
            $('#dialogWindow').append("<span id='dialogWindowText' style='text-align:center;padding:8px;padding-right:16px;padding-left:16px;display:inline-block;color:white;border-radius:3px;font-size:80%;'>"+texte+"</span>");
            
            
            //Changer le proprietes css
            $('#dialogContent').css("top",topPos);
            $('#dialogContent').css("left",leftPos);
            $('#dialogWindowText').css("color",color);
            $('#dialogWindowText').css("background-color",colorContent);
            
            //Afficher le dialog
            $('#dialogContent').fadeIn();
          };

          $scope.hideDialogLoading =function() {
            $('#dialogContent').fadeOut(function(){
              $('#dialogContent').remove();
            });
          };

           $scope.hellordWordDialog = function() {
            
            //On affiche le dialog
            $scope.showDialogLoading("Chargement ...","white","#9370db","0%","0%");

            setTimeout(function(){
              $scope.hideDialogLoading();
            },3000);
          };

           /***  
               verifie si c'est une operation de creation
           **/
          $scope.iscreateOperation = function(){
               return $scope.windowType == 'new';
          };
          
          $scope.shownotepanel = function(){
              if($scope.windowType == 'new'){
                  return false;
              }
              if($scope.metaData.activatefollower==true){
                  return true;
              }
              return false;
          };

          $scope.isviewOperation = function(){
              return $scope.windowType == 'view';
          };

          /***  
               verifie si c'est une operation de creation
           **/
          $scope.isupdateOperation = function(){
               return $scope.windowType == 'update';
          };

          /**
             Selectionne ou desellectionn les lignes du tableaux
          **/
          $scope.onCheckboxClick = function(){
        
            $scope.tableheaderselected = !$scope.tableheaderselected;
            $scope.selectedObjects = [];
               
               for(var i=0 ; i<$scope.datas.length;i++){
                  module = $scope.datas[i];               
                  module.selected = $scope.tableheaderselected;

                  if(module.selected){
                       $scope.selectedObjects.push(module);
                  }
               }                       
               
          };
  
          /**
             Verifie que le tableau contient un element
             @array : tableau de données
             @item : element
          **/
          $scope.contains = function(array , item){
               if(!angular.isDefined(array)){
                  return false;
               }

               for(var i= 0 ; i<array.length;i++){

                   if(array[i].id == item.id){
                      return true;
                   }
               }

               return false;
          };
          
           /**
             Verifie que le tableau contient un element
             @array : tableau de données
             @item : element
          **/
          $scope.containsObject = function(array , item){
               if(!angular.isDefined(array)){
                  return false;
               }

               for(var i= 0 ; i<array.length;i++){

                   if(array[i].compareid == item.compareid){
                      return true;
                   }
               }

               return false;
          };
          
          /**
           * Return the index of Item
           * @param {type} array
           * @param {type} item
           * @returns {undefined}
           */
          $scope.indexOf = function(array , item){
              if(!angular.isDefined(array)){
                  return -1;
               }

               for(var i= 0 ; i<array.length;i++){

                   if(array[i].compareid == item.compareid){
                      return i;
                   }
               }

               return -1;
          };

           /**
            * Retire une valeur d'un tableau
            * @param {type} array
            * @param {type} item
            * @returns {undefined}
            */
          $scope.removeFromArray = function(array , item){
               if(angular.isDefined(array)){
                  
                  for(var i=0 ; i<array.length;i++){
                     if(array[i].compareid == item.compareid){
                        array.splice(i , 1);
                     }
                  }
                  //console.log(array+" ====== "+item);                             
              }
          };

           /**
            * 
            * @param {type} item
            * @returns {undefined}
            */
          $scope.onRowCheckboxClickDialog = function(item){
              
              //Verifier que 
              if(!$scope.dataCache.selectedObjects){
                  $scope.dataCache.selectedObjects = new Array();
              }
              item.selected = !item.selected;

              if(item.selected){
                if(!$scope.containsObject($scope.dataCache.selectedObjects , item)){
                     //console.log("$scope.onRowCheckboxClickDialog = ============== "+item);
                     $scope.dataCache.selectedObjects.unshift(item);
                }else{
                    
                    item.selected = !item.selected; 
                    var message ="Vous avez selectionné 2 lignes referencant la même entité";
                    commonsTools.notifyWindow("Double selection " ,"<br/>"+message,"warning");
                }
              }else{
                //console.log("$scope.onRowCheckboxClickDialog $scope.containsObject($scope.dataCache.selectedObjects , item) = ============== "+item);
                $scope.removeFromArray($scope.dataCache.selectedObjects , item);                
              }             

          };
          
          /**
           * 
           * @param {type} item
           * @returns {undefined}
           */
          $scope.onRowCheckboxClick = function(item){
              //console.log("============== "+item);
              item.selected = !item.selected;

              if(item.selected){
                if(!$scope.contains($scope.selectedObjects , item)){
                     $scope.selectedObjects.unshift(item);
                }
              }else{
                $scope.removeFromArray($scope.selectedObjects , item);
                if($scope.selectedObjects.length==0){
                  $scope.tableheaderselected = false;
                }
              }
              $scope.currentObject = $scope.selectedObjects[0];

          };



         /**
          * 
          * @returns {undefined}
          */
         $scope.tableListBuilder = function(){
            
             var tableElem = document.createElement('table');
             tableElem.setAttribute('class' , 'table table-striped table-hover table-responsive');
             tableElem.setAttribute('id' , 'table');
             //Creation du table header
             var theadElem = document.createElement('thead');
             tableElem.appendChild(theadElem);
             //Creation entete
             var  rheadElem = document.createElement('tr');
             theadElem.appendChild(rheadElem);
             var thElem0 = document.createElement('th');
             var inputElem0 = document.createElement('input');
             inputElem0.setAttribute('type' , 'checkbox');
             inputElem0.setAttribute('ng-model' , 'tableheaderselected');
             inputElem0.setAttribute('ng-click' , 'onCheckboxClick()');
            thElem0.appendChild(inputElem0);
            rheadElem.appendChild(thElem0);
            metaData.columns = $filter('orderBy')(metaData.columns,'colsequence',false);    
            for(var i=0 ; i<$scope.metaData.columns.length;i++){
              if(angular.isDefined($scope.metaData.columns[i].search)
                        &&($scope.metaData.columns[i].search==true)){
                  var thElem = document.createElement('th');
                  thElem.innerHTML = $scope.metaData.columns[i].fieldLabel;
                  rheadElem.appendChild(thElem);
              }
            }

            //Creation du corps du tableau
            var tbodyElem = document.createElement('tbody');
            tableElem.appendChild(tbodyElem);
            var rbodyElem = document.createElement('tr');
            rbodyElem.setAttribute('ng-repeat' , ' obj in datas');
            tbodyElem.appendChild(rbodyElem);
            var tdElem = document.createElement('td');
            rbodyElem.appendChild(tdElem);
             inputElem0 = document.createElement('input');
             inputElem0.setAttribute('type' , 'checkbox');
             inputElem0.setAttribute('ng-model' , 'obj.selected');
             inputElem0.setAttribute('ng-click' , 'onRowCheckboxClick(obj)');
             tdElem.appendChild(inputElem0);
             
             for(var i=0 ; i<$scope.metaData.columns.length;i++){
                  if(angular.isDefined($scope.metaData.columns[i].search)
                        &&($scope.metaData.columns[i].search==true)){
                  var thElem = document.createElement('td');
                  thElem.innerHTML = "{{obj."+$scope.metaData.columns[i].fieldName+"}}";
                  rbodyElem.appendChild(thElem);
              }
             }

            var divElem = document.createElement('div');
            divElem.appendChild(tableElem);
            var viewElem = angular.element(divElem.innerHTML);
            var compileFn = $compile(viewElem);
            compileFn($scope);

            var items = $element.find("div");
            for(var i=0; i<items.length;i++){
                 
                 if(items.eq(i).attr("id")=="datatable"){
                       items.eq(i).replaceWith(viewElem);
                 }  
            }
         };

         /**
             Fonction de construction du filtre de recherche
         **/
         $scope.filterOptionsBuilder = function(){
               
               var ulElem = document.createElement('ul');
               ulElem.setAttribute('class','dropdown-menu');
               ulElem.setAttribute('role' , 'menu');
               ulElem.setAttribute('aria-labelledby' , 'filterbtn');
               ulElem.setAttribute('id' , 'filterActionsId');
               ulElem.setAttribute('ng-repeat' , ' obj in metaData');
                
               for(var i=0 ; i< $scope.metaData.columns.length;i++){
                 if(angular.isDefined($scope.metaData.columns[i].search)
                        &&($scope.metaData.columns[i].search==true)){
                   var liElem = document.createElement('li');
                   liElem.setAttribute('role' , 'presentation');
                   ulElem.appendChild(liElem);
                   var aElem = document.createElement('a');
                   aElem.setAttribute('role' , 'menuitem');
                   aElem.setAttribute('tabindex' , '-1');
                   aElem.setAttribute('href' , '#');
                   liElem.appendChild(aElem);
                   var divElem = document.createElement('div');
                   aElem.appendChild(divElem);                   
                   var inputElem = document.createElement('input');
                   divElem.appendChild(inputElem);
                   divElem.setAttribute('style' , 'border:solid 1px red;');
                   inputElem.setAttribute('ng-model' , 'obj.value');
                   inputElem.setAttribute('class' , 'input-sm');
                   inputElem.setAttribute('placeholder' , $scope.metaData.columns[i].fieldLabel);
                   inputElem = document.createElement('input');
                   divElem.appendChild(inputElem);
                   inputElem.setAttribute('type' , 'checkbox');
                   inputElem.setAttribute('class' , 'input-sm');
                   inputElem.setAttribute('placeholder' , $scope.metaData.columns[i].fieldLabel);
                 }
               } 
                var divElem = document.createElement('div');
                divElem.appendChild(ulElem);
                var viewElem = angular.element(divElem.innerHTML);
                var compileFn = $compile(viewElem);
                compileFn($scope);

                var items = $element.find("ul");
                for(var i=0; i<items.length;i++){
                     
                     if(items.eq(i).attr("id")=="filterActionsId"){
                           items.eq(i).replaceWith(viewElem);
                     }  
                }
                //alert(divElem.innerHTML);
         };


          /**
             Application des contraints sur les champs
          **/
         $scope.constraintsProvider = function(field , htmlElem){
            
             if(angular.isObject(field) && htmlElem){
               // console.log("$scope.constraintsProvider = function(field , htmlElem){ ==== "+field.optional+" ==== "+field.fieldName);
                  //Cas des champs obligatoire.
                  if(angular.isDefined(field.optional)&& !field.optional){                    
                      htmlElem.setAttribute("ng-required",true);
                  }

                  if(field.min && angular.isNumber(field.min)){
                      htmlElem.setAttribute("ng-minlength",field.min);
                      if(!angular.isDefined(field.optional) || field.optional){                    
                          htmlElem.setAttribute("ng-required",true);
                      }
                  }

                  if(field.max && angular.isNumber(field.max)){
                      htmlElem.setAttribute("ng-maxlength",field.max);
                  }

                  if(field.pattern && (field.type=="string") && angular.isString(field.pattern)){                    
                      htmlElem.setAttribute("ng-pattern",new RegExp(field.pattern));
                  }
                  
                  //Interdir de modification en mise a jour
                  if($scope.windowType=="update"&&$scope.innerWindowType!='new'){
                      if(!field.updatable){
                          htmlElem.setAttribute("readonly","readonly");
                      }
                  }


             }
         };
         
         /**
          * 
          * @param {type} model
          * @param {type} field
          * @param {type} labelText
          * @param {type} entityName
          * @param {type} type
          * @returns {undefined}
          */
         $scope.richEditorComponent = function(model , field , labelText, entityName,type){
             //console.log('$scope.richEditorComponent = '+model);
             var divElem = document.createElement('div'); //name='"+field.fieldName+"'
             divElem.innerHTML="<div text-angular ng-model="+model+"  ta-html-editor-class='border-around'>  </div>";
             //alert(divElem.innerHTML);
             return divElem;
         };
         
         /**
          * 
          * @param {type} model
          * @param {type} field
          * @param {type} labelText
          * @param {type} entityName
          * @param {type} type
          * @returns {principal_L351.principalAnonym$23.controller.$scope.richEditorComponent.divElem|Element}
          */
          $scope.aceEditorComponent = function(model , field , labelText, entityName,type){
             //console.log('$scope.aceEditorComponent = '+model);
             var divElem = document.createElement('div'); //name='"+field.fieldName+"'
             divElem.innerHTML="<div class='editor' ng-model="+model+"  data-ace=''>  </div>";
             //alert(divElem.innerHTML);
             return divElem;
         };

         /**
            Creation des composants
            model : field to map
            labelText : the Label of the input
            entityName : name of the entity
            type:type of data can be (text ,search, email ,url,tel,password,number ,
                                     datetime-local , date , month , week , time , color
                                     ,checkbox , radio  )
         ***/
         $scope.textAreaBuilder = function(model , field , labelText, entityName,type){
                   var divElem = document.createElement('div');
                   divElem.setAttribute('class' , 'form-group') ;                   
                   var labelElem = document.createElement('label');
                   labelElem.setAttribute('for' , field.fieldName);
                   labelElem.appendChild(document.createTextNode(labelText));              
                   divElem.appendChild(labelElem);
                   var textareaElem = document.createElement("textarea");
                   textareaElem.setAttribute('class' , 'form-control');
                   if(angular.isDefined(field.optional)&&(!field.optional) || field.min){
                         textareaElem.setAttribute('class' , 'form-control required') ;        
                   }
                   textareaElem.setAttribute('name' , field.fieldName);
                   textareaElem.setAttribute('placeholder' , labelText+" ....");
                   textareaElem.setAttribute('rows' ,'3');
                   textareaElem.setAttribute('ng-model' , model);
                   $scope.constraintsProvider(field , textareaElem);
                   divElem.appendChild(textareaElem);
                   return divElem;
              } 
        
       /**
        * 
        * @param {type} model
        * @param {type} field
        * @param {type} labelText
        * @param {type} entityName
        * @param {type} type
        * @returns {Element}
        */
         $scope.inputTextBuilder = function(model , field , labelText, entityName,type){
                   var divElem = document.createElement('div');
                   divElem.setAttribute('class' , 'form-group form-group-sm col-sm-12  col-md-12') ;                   
                   var labelElem = document.createElement('label');
                   labelElem.setAttribute('for' , field.fieldName);
                   labelElem.appendChild(document.createTextNode(labelText)); 
                   /*if(angular.isDefined(field.optional)&&(!field.optional) || field.min){
                          labelElem.appendChild(document.createTextNode(labelText+"(*)")); 
                   } else{
                     
                   } */
                   divElem.appendChild(labelElem);
                   //Creation du composant input
                    var inputElem = document.createElement('input');
                    inputElem.setAttribute('name' , field.fieldName);                        
                    if(type!="checkbox"){
                        inputElem.setAttribute('class' , 'form-control');
                        inputElem.setAttribute('placeholder' , labelText+" .... ");
                        if(angular.isDefined(field.optional)&&(!field.optional)){
                                inputElem.setAttribute('class' , 'form-control required');
                                inputElem.setAttribute('ng-required' , 'true');
                         } 
                         if((field.min && field.min>0)){
                             inputElem.setAttribute('ng-minlength' , field.min);
                         }
                         if(field.max && field.max>0){
                             inputElem.setAttribute('ng-maxlength' , field.max);
                         }
                         if(field.pattern){
                             inputElem.setAttribute('ng-pattern' , field.pattern);
                         }
                         
                         if(type=="number"){
                             inputElem.setAttribute('value' , "0");
                         }
                         
                    }
//                    console.log("================================ "+$scope.innerWindowType+"==============="+($scope.windowType=="view")+"===="+((field.updatable==false)&&($scope.windowType!='new')&&($scope.innerWindowType!='new'))+" === "+(field.editable==false)+" ================= "+(($scope.windowType=="view")||((field.updatable==false)&&($scope.windowType!='new')&&($scope.innerWindowType!='new'))||(field.editable==false)));
                    inputElem.setAttribute('type' , type);
                    inputElem.setAttribute('ng-model' , model);
                    $scope.constraintsProvider(field , inputElem);
                    if(($scope.windowType=="view")||((field.updatable==false)&&($scope.windowType!='new')&&($scope.innerWindowType!='new'))||(field.editable==false)){
                        inputElem.setAttribute('readonly' , 'readonly');
                    }
                    if(field.hide){
                        divElem.setAttribute('ng-hide',true);
                    }
                    divElem.appendChild(inputElem);              
                  return divElem;
              };
              
           $scope.lineTextBuilder = function(field , value){
                   var divElem = document.createElement('div');
                   divElem.setAttribute('class' , 'form-group form-group-sm col-sm-12  col-md-12') ;                   
                   var labelElem = document.createElement('span');
//                   labelElem.setAttribute('for' , field.fieldName);
                  labelElem.appendChild(document.createTextNode(field.fieldLabel));
                   divElem.appendChild(labelElem);
                   //Creation du composant input
                    var inputElem = document.createElement('span');
//                    inputElem.setAttribute('name' , field.fieldName);                
//                    console.log("================================ "+$scope.innerWindowType+"==============="+($scope.windowType=="view")+"===="+((field.updatable==false)&&($scope.windowType!='new')&&($scope.innerWindowType!='new'))+" === "+(field.editable==false)+" ================= "+(($scope.windowType=="view")||((field.updatable==false)&&($scope.windowType!='new')&&($scope.innerWindowType!='new'))||(field.editable==false)));
                     if(angular.isObject(value)){
                       inputElem.appendChild(document.createTextNode(value['designation']));
                   }else if(value){
                       inputElem.appendChild(document.createTextNode(value));
                   }else{
                       inputElem.appendChild(document.createTextNode(""));
                   }              
                    divElem.appendChild(inputElem);              
                  return divElem;
              };

         /**
            Creation des composants
            model : field to map
            labelText : the Label of the input
            entityName : name of the entity
            type:type of data can be (text ,search, email ,url,tel,password,number ,
                                     datetime-local , date , month , week , time , color
                                     ,checkbox , radio  )
         ***/
         $scope.checkboxBuilder = function(model , field , labelText, entityName,type){
                   var divElem = document.createElement('div');
                   divElem.setAttribute('class' , 'form-check  col-sm-12  col-md-12') ;                   
                   var labelElem = document.createElement('label');
                   labelElem.setAttribute('class' ,'form-check-label');
                   labelElem.setAttribute('for' , field.fieldName);
                   divElem.appendChild(labelElem);
                   //Creation du composant input
                    var inputElem = document.createElement('input');
                    inputElem.setAttribute('type' , 'checkbox');
                    //inputElem.setAttribute('checked' , '');
                    inputElem.setAttribute('data-toggle' , 'toggle');
                    inputElem.setAttribute('ng-model' , model);
                     inputElem.setAttribute('name' , field.fieldName);       
                    $scope.constraintsProvider(field , inputElem);
                    if(($scope.windowType=="view")||
                            ((field.updatable==false)&&($scope.windowType!='new')&&($scope.innerWindowType==false))||(field.editable==false)){
                        inputElem.setAttribute('readonly' , 'readonly');
                    }
                    labelElem.appendChild(inputElem);  
                    labelElem.appendChild(document.createTextNode(labelText));                                      
//                    var divElem0 = document.createElement('div');
//                   divElem0.appendChild(divElem);
//                  
//                  alert(divElem0.innerHTML);
                  return divElem;
              } ;
          
          /**
           * 
           * @param {type} key
           * @returns {undefined}
           */
        $scope.imageClick = function(key){
            $("#"+key).trigger('click');
//            console.log('$scope.imageClick ==========================='+key);
         };
        
         /**
          * 
          * @param {type} imageChooserInput
          * @param {type} imageContent
          * @param {type} apercuImageContent
          * @returns {undefined}
          */ 
        $scope.gererChangementImage = function(event,model,imageChooserInput,imageContent,apercuImageContent,field){
              //Initiallisation du tableau des images
              var part =model.split(",");
              var subpart = part[0].split(".");
              var fileInput = document.querySelector('#'+imageChooserInput);
              var file = event.target.files[0];              
              var allowedTypes = ['png', 'jpg', 'jpeg', 'gif'];
              var imgType = file.name.split(".");
              var date = new Date();
              var filename = date.getTime()+".png";
              field = subpart[1];
              var data = $scope.currentObject;
              if(angular.lowercase(subpart[0])!="currentobject"){
                  data = $scope.temporalData;
              }
//              console.log("$scope.gererChangementImage = function(imageChooserInput,imageContent,apercuImageContent) ======= "+field+" === "+data[field]);
              if(!data[field]||data[field]==""){
                  data[field] = filename;
              }else{
                  filename = data[field];
              }
              imgType = imgType[imgType.length-1].toLowerCase();
              if(allowedTypes.indexOf(imgType)!=-1){
                  var reader = new FileReader();
                  reader.onload = function(){
                      var imgElement = document.querySelector('#'+imageContent);      
		      imgElement.src = this.result;   
                  };
                  reader.readAsDataURL(file);
              }
              if(!$scope.dataCache['resources']){
                  $scope.dataCache['resources'] = new Array();
              }
              if(!$scope.dataCache['names']){
                  $scope.dataCache['names'] = new Array();
              }
//              var map = new Map();
//              map.set(filename,file);
              $scope.dataCache['resources'].push(file);
              $scope.dataCache['names'].push(filename);
              //commonsTools.gererChangementImage(imageChooserInput,imageContent,apercuImageContent);
         };
         
         /**
          * 
          * @param {type} event
          * @param {type} model
          * @param {type} inputID
          * @param {type} imageContent
          * @returns {undefined}
          */
         $scope.gererChangementFichier = function(event){
              //Initiallisation du tableau des images
//              var fileInput = document.querySelector('#'+inputID);
//              console.log("$scope.gererChangementFichier = function(event) =============== ");
             var file = event.target.files[0];              
              var imgType = file.name.split(".");
              imgType = imgType[imgType.length - 1].toLowerCase();
              var date = new Date();
              var filename = date.getTime()+"."+imgType;             
              if(!$scope.dataCache['resources']){
                  $scope.dataCache['resources'] = new Array();
              }
              if(!$scope.dataCache['names']){
                  $scope.dataCache['names'] = new Array();
              }
//              var map = new Map();
//              map.set(filename,file);
              $scope.dataCache['resources'].push(file);
              $scope.dataCache['names'].push(filename);
              //transfert des resources et mise a jour du menu
              var url = "http://"+$location.host()+":"+$location.port()+"/kerencore/piecejointe";
              var data = {id:-1,compareid:-1,designation:"",editTitle:""
                    ,listTitle:"",moduleName:'kerencore',selected:false,createonfield:true,desablecreate:false,
                    serial:"1234",activefilelien:false,desabledelete:false,filename:filename,attachename:file.name,entityserial:$scope.currentObject.serial,entityid:$scope.currentObject.id};
             commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%"); 
             $http.post(url,data)
                      .then(function(response){
                           var url2 = "http://"+$location.host()+":"+$location.port()+"/kerencore/resource/upload";
                           $http.defaults.headers.common['names']= angular.toJson($scope.dataCache['names']); 
                                                restService.uploadFile($scope.dataCache['resources'])
                                                    .then(function(response){
//                                                        alert(angular.toJson(response))                                                          
                                                        $scope.dataCache['resources'] = new Array();
                                                        $scope.dataCache['names'] = new Array(); 
//                                                        commonsTools.hideDialogLoading();
                                                        $scope.piecejointeMenu(null,$scope.currentObject,$scope.metaData);
                                                    },function(error){
                                                        $scope.dataCache['resources'] = new Array();
                                                        $scope.dataCache['names'] = new Array();   
                                                        commonsTools.hideDialogLoading();
                                                        $scope.notifyWindow("ERREUR" ,"Le transfert des ressources a échoué <br> Veuillez consulter les logs pour plus de détails","success");
                                                    });     
                      },function(error){
                          commonsTools.showMessageDialog(error);
                          commonsTools.hideDialogLoading();
                      }); 
             //commonsTools.gererChangementImage(imageChooserInput,imageContent,apercuImageContent);
         
         
         };
         
        /**
         * 
         * @param {type} model
         * @param {type} field
         * @param {type} labelText
         * @param {type} entityName
         * @param {type} type
         * @returns {Element}
         */
        $scope.imageComponentBuilder = function(model , field , labelText, entityName,type){
                   var part = model.split(".");
                   var data = $scope.currentObject;
                   if(angular.lowercase(part[0])!="currentobject"){
                       data = $scope.temporalData;
                   }
//                   var url = "http://"+$location.host()+":"+$location.port()+"/kerencore/resource/png/"+(data[field.fieldName]);
//                   console.log('$scope.imageComponentBuilder ============ '+url+"  === "+angular.toJson(data)+"====="+data[field.fieldName]+" ==== "+field.fieldName);  
                   var parts = model.split('.');
                   var divElem = document.createElement('div');
                   divElem.setAttribute('id',parts[1]+'ImageContent');
                   divElem.setAttribute('class','form-group  col-sm-12  col-md-12');
                   //divElem.setAttribute('style','width:10px;');
                   var imgElem = document.createElement('img');
                   imgElem.setAttribute('id',parts[1]+'apercuImageContent');
//                   imgElem.setAttribute('ng-src',url);
                   imgElem.setAttribute('ng-src',"img\\photo.png");
                   imgElem.setAttribute('class','img-responsive');
                   imgElem.setAttribute('alt',field.fieldLabel);
                   imgElem.setAttribute('height','50');
                   imgElem.setAttribute('width','75');
                   imgElem.setAttribute('ng-click',"imageClick('"+parts[1]+"imageChooserInput')");
                   divElem.appendChild(imgElem);
                   var inputElem = document.createElement('input');
                   inputElem.setAttribute('id',parts[1]+'imageChooserInput');
                   inputElem.setAttribute('style','display: none');
                   inputElem.setAttribute('type','file');
                   inputElem.setAttribute('fileinput','file');
                   var vname = field.fieldName;
                   inputElem.setAttribute('onchange',"angular.element(this).scope().gererChangementImage(event,'"+model+","+parts[1]+"imageChooserInput','"+parts[1]+"imageContent' ,'"+parts[1]+"apercuImageContent','"+vname+"')");
                   inputElem.setAttribute('filepreview',"'"+model+"'");
                   inputElem.setAttribute('accept','image/x-png');
                   divElem.appendChild(inputElem);
                   restService.downloadPNG(data[field.fieldName],parts[1]+'apercuImageContent');
//                   var scriptElem = document.createElement('script');
//                   scriptElem.innerHTML="gererChangementImage('"+parts[1]+"imageChooserInput','"+parts[1]+"imageContent' ,'"+parts[1]+"apercuImageContent')";
//                   divElem.appendChild(scriptElem);
//                   var div = document.createElement('div');
//                   div.appendChild(divElem);
//                   alert(div.innerHTML);
                   return divElem;
              };  

        /**
         * 
         * @param {type} model
         * @param {type} field
         * @param {type} labelText
         * @param {type} entityName
         * @param {type} type
         * @returns {Element}
         */
        $scope.emptyComponentBuilder = function(model , field , labelText, entityName,type){
                   var divElem = document.createElement('div');
                   divElem.setAttribute('class' , 'form-group  col-sm-12  col-md-12') ;
                   
                   var labelElem = document.createElement('span');
                   labelElem.setAttribute('for' , field.fieldName);
                   //labelElem.appendChild(document.createTextNode(labelText)); 
                   divElem.appendChild(labelElem);
                   //Creation du composant input
                  var inputElem = document.createElement('label');
                  inputElem.setAttribute('class' , 'form-control-plaintext');
                  //inputElem.setAttribute('style','border-style:none;');
                  //inputElem.setAttribute('name' , entityName);
                  //inputElem.setAttribute('placeholder' , labelText+" .... ");
                   if($scope.windowType=="view"||(field.updatable==false&&$scope.windowType!='new')){
                        inputElem.setAttribute('readonly' , 'readonly');
                    }
                  //inputElem.setAttribute('type' , type);
                  //inputElem.setAttribute('ng-model' , model);
                  divElem.appendChild(inputElem);
                  var divElem0 = document.createElement('div');
                  divElem0.appendChild(divElem);
                  
                  //alert(divElem0.innerHTML);
                  return divElem;
              } ;       
        

/**
 * 
 * @param {type} event
 * @returns {undefined}
 */
   $scope.gererChangementFichier2 = function(event){
              //Initiallisation du tableau des images
//              var fileInput = document.querySelector('#'+inputID);
//              console.log("$scope.gererChangementFichier = function(event) =============== ");
             var file = event.target.files[0];              
              var imgType = file.name.split(".");
              imgType = imgType[imgType.length - 1].toLowerCase();
              var date = new Date();
              var filename = date.getTime()+"."+imgType;             
              if(!$scope.dataCache['resources']){
                  $scope.dataCache['resources'] = new Array();
              }
              if(!$scope.dataCache['names']){
                  $scope.dataCache['names'] = new Array();
              }
//              var map = new Map();
//              map.set(filename,file);
              $scope.dataCache['resources'].push(file);
              $scope.dataCache['names'].push(filename);
              //transfert des resources et mise a jour du menu
//              var url = "http://"+$location.host()+":"+$location.port()+"/kerencore/piecejointe";
              var data = {id:-1,compareid:-1,designation:"",editTitle:""
                    ,listTitle:"",moduleName:'kerencore',selected:false,createonfield:true,desablecreate:false,
                    serial:"1234",activefilelien:false,desabledelete:false,filename:filename,attachename:file.name,entityserial:null,entityid:null};
             $scope.dataCache["messageobject"].piecesjointe.push(data);
             commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%"); 
//             var url2 = "http://"+$location.host()+":"+$location.port()+"/kerencore/resource/temporalupload";
//             console.log("$scope.gererChangementFichier = function(event) =============== "+angular.toJson($scope.dataCache["messageobject"]));
            $http.defaults.headers.common['names']= angular.toJson($scope.dataCache['names']); 
            restService.uploadFile2($scope.dataCache['resources'])
                .then(function(response){//                                                        alert(angular.toJson(response))                                                          
                    $scope.dataCache['resources'] = new Array();
                    $scope.dataCache['names'] = new Array(); 
                    $scope.followerpiecejointeMenu($scope.dataCache["messageobject"]);
                    commonsTools.hideDialogLoading();                                                        
                },function(error){
                    $scope.dataCache['resources'] = new Array();
                    $scope.dataCache['names'] = new Array();   
                    commonsTools.hideDialogLoading();
                    commonsTools.showDialogLoading(error);
//                    commonsTools.notifyWindow("ERREUR" ,"Le transfert des ressources a échoué <br> Veuillez consulter les logs pour plus de détails","success");
                }); 
                      
             //commonsTools.gererChangementImage(imageChooserInput,imageContent,apercuImageContent);
         
         
         };

        /**
             Create component for ManyToOne relashion 
             model : field to map
            labelText : the Label of the input
            entityName : name of the entity,
            metaData : MetaData which describe de object
        **/
        $scope.comboBoxComponent = function(model , field , labelText , entityName,value){
               //Creation de l'entree
                //var exprFn = $parse(model) 
                //var data = exprFn($scope);
                var parts = value.split(";");
                
               //console.log("$scope.manyToOneComponent    ===      "+model+" :::::::  !!!!!!!! "+metaData.entityName);
               var divElem = document.createElement('div');
               divElem.setAttribute('class' , 'form-group form-group-sm col-sm-12  col-md-12');
               var labelElem = document.createElement('label');
               labelElem.setAttribute('for' , field.fieldName);
               labelElem.appendChild(document.createTextNode(labelText));
               divElem.appendChild(labelElem);
               var divElem_1 = document.createElement('div');
               divElem_1.setAttribute('class','input-group');
               divElem.appendChild(divElem_1);
               var selectElem = document.createElement('select');
               selectElem.setAttribute('class','form-control');
               selectElem.setAttribute('data-style' , 'btn-default');
               selectElem.setAttribute('ng-model' , model);
               //selectElem.setAttribute('ng-change' , "getData('"+model+"',"+entityName+")");
               divElem_1.appendChild(selectElem);
              //selectElem.setAttribute('ng-options' , "item as item.designation for item in dataCache."+parts[1]);
              $scope.constraintsProvider(field , selectElem);
//              if($scope.windowType=="view"||(field.updatable&&field.updatable==false&&$scope.windowType!='new'&&$scope.innerWindowType==false)){
//                  selectElem.setAttribute('disabled' , 'disabled');
//              }
              if(($scope.windowType=="view")||
                        ((field.updatable==false)&&($scope.windowType!='new')&&($scope.innerWindowType==false))||(field.editable==false)){
                    selectElem.setAttribute('disabled' , 'true');
                }
              var optionElem = document.createElement('option');
              optionElem.setAttribute('value' , '');
              optionElem.appendChild(document.createTextNode('Please select option'));
              selectElem.appendChild(optionElem);
              for(var i=0 ; i<parts.length;i++){
                  var optionElem = document.createElement('option');
                  optionElem.setAttribute('value' , ""+i);
                  optionElem.appendChild(document.createTextNode(parts[i]));
                  selectElem.appendChild(optionElem);
                  //console.log("comboBoxComponent ===== "+i+" ====== "+parts[i]);   
              }             
             
              return divElem;
        };

        /**
             Create component for ManyToOne relashion 
             model : field to map
            labelText : the Label of the input
            entityName : name of the entity,
            metaData : MetaData which describe de object
        **/
        $scope.manyToOneComponent = function(model , labelText , entityName,metaData,field,index,modelpath){               
//               $scope.currentMetaDataPath = $scope.getMetaDataPath(metaData);
               //Creation de l'entree
               var exprFn = $parse(model); 
                var data = exprFn($scope);
                var parts = model.split(".");
                var key = commonsTools.keygenerator(model);
                if(parts.length>1){
                    $scope.dataCache[""+key+""] = new Array();
                    if(data){
                      $scope.dataCache[""+key+""].push(data);
                    }
                    var obj = {id:'load' , designation:'Charger les données ....'};
//                    if($scope.dataCache[parts[1]]&&$scope.dataCache[parts[1]].length>0){
//                        obj = {id:'loadwithsearch' , designation:'Chercher plus ...'};
//                    }
                    $scope.dataCache[""+key+""].push(obj);
                }
//                console.log("$scope.manyToOneComponent ============== "+index+" ==== model : "+model+" :::: key:"+key+" == "+angular.toJson($scope.dataCache[""+key+""]));
               //console.log("$scope.manyToOneComponent    ===      "+model+" :::::::  !!!!!!!! "+metaData.entityName);
               var divElem = document.createElement('div');
               divElem.setAttribute('class' , 'form-group form-group-sm col-sm-12  col-md-12');
               var labelElem = document.createElement('label');
               labelElem.setAttribute('for' , entityName);
               labelElem.appendChild(document.createTextNode(labelText));
               divElem.appendChild(labelElem);
               var divElem_1 = document.createElement('div');
               divElem_1.setAttribute('class','input-group');
               divElem.appendChild(divElem_1);
               var selectElem = document.createElement('select');
               selectElem.setAttribute('class','selectpicker form-control');
               selectElem.setAttribute('data-style' , 'btn-default');
               selectElem.setAttribute('ng-model' , model);
               selectElem.setAttribute('ng-change'  , "getData('"+model+"',item,'"+metaData.entityName+"','"+metaData.moduleName+"',"+(index+1)+",'"+modelpath+"')");
               selectElem.setAttribute('data-live-search','true');
               divElem_1.appendChild(selectElem);
              selectElem.setAttribute('ng-options' , "item as item.designation for item in dataCache."+key);
              var optionElem = document.createElement('option');
              optionElem.setAttribute('value' , '');
              optionElem.appendChild(document.createTextNode('Please select option'));
              selectElem.appendChild(optionElem);
//              if($scope.windowType=="view"||(field.updatable==false&&$scope.windowType!='new')){
//                  selectElem.setAttribute('disabled' , 'disabled');
//              }
              if(($scope.windowType=="view")||
                        ((field.updatable==false)&&($scope.windowType!='new')&&($scope.innerWindowType==false))){
                    selectElem.setAttribute('disabled' , 'true');
//                    buttonElem.setAttribute('disabled' , 'disabled');
                }
              //Desactiver la creation
            var spanElem = document.createElement('span');
            spanElem.setAttribute('class' , 'input-group-btn');
            divElem_1.appendChild(spanElem);
            var buttonElem = document.createElement('button');
            buttonElem.setAttribute('class' , 'btn btn-default btn-sm my-group-button');
            buttonElem.setAttribute('ng-click' , "editDialogBuilder('"+model+"',null,'new_entity','"+metaData.entityName+"','"+metaData.moduleName+"',"+(index+1)+",'"+modelpath+"')");
            buttonElem.setAttribute('data-toggle' , "modal");            
//            buttonElem.setAttribute('data-target' , '#myModal');
            var nextIndex = index+1;            
            if(nextIndex==1){
                buttonElem.setAttribute('data-target', '#myModal');   
            }else if( nextIndex==2){
                buttonElem.setAttribute('data-target', '#globalModal');
            }else if( nextIndex==3){
                buttonElem.setAttribute('data-target', '#myModal1');
            }else if( nextIndex==4){
                buttonElem.setAttribute('data-target', '#myModal2');
            }//end if(index==1)           
            spanElem.appendChild(buttonElem);
            
            var spanElem_1 = document.createElement('span');
            spanElem_1.setAttribute('class','glyphicon glyphicon-plus');
            spanElem_1.setAttribute('aria-hidden' , 'true');
            spanElem_1.setAttribute('style' , 'color:blue');
            buttonElem.appendChild(spanElem_1);

            if(($scope.windowType=="view")||(metaData.createonfield==false)||((field.updatable==false)&&($scope.windowType!='new')&&($scope.innerWindowType==false))){
                buttonElem.setAttribute('disabled' , 'disabled');
            }
              return divElem;
        };

      

         /**
          * 
          * @param {type} model
          * @param {type} labelText
          * @param {type} entityName
          * @param {type} metaData
          * @param {type} field
          * @param {type} index
          * @param {type} modelpath
          * @returns {Element}
          */
        $scope.manyToManyComponent = function(model , labelText , entityName,metaData,field,index,modelpath){
               var exprFn = $parse(model) 
                var data = exprFn($scope);
                var parts = model.split(".");
                var key = commonsTools.keygenerator(model);
                if(parts.length>1){
                    $scope.dataCache[""+key+""] = new Array();
                    if(data){
                       for(var i=0 ; i<data.length;i++){
                           $scope.dataCache[""+key+""].push(data[i]);
                       }                       
                    }
                    var obj = {id:'load' , designation:'Charger les données ....'};
//                    if($scope.dataCache[parts[1]]&&$scope.dataCache[parts[1]].length>0){
//                        obj = {id:'loadwithsearch' , designation:'Chercher plus ....'};
//                    }
                    $scope.dataCache[""+key+""].push(obj);
                }
//                console.log("$scope.manyToManyComponent = === "+model+" ==== "+parts[1]);
//               $scope.currentMetaDataPath = $scope.getMetaDataPath(metaData);
               var divElem = document.createElement('div');
               divElem.setAttribute('class' , 'form-group form-group-sm col-sm-12  col-md-12');
               var labelElem = document.createElement('label');
               labelElem.setAttribute('for' , entityName);
               labelElem.appendChild(document.createTextNode(labelText));
               divElem.appendChild(labelElem);
               var divElem_1 = document.createElement('div');
               divElem_1.setAttribute('class','input-group');
               divElem.appendChild(divElem_1);
               var selectElem = document.createElement('select');
               selectElem.setAttribute('class','selectpicker form-control');
               selectElem.setAttribute('multiple' , 'multiple');
               selectElem.setAttribute('data-style' , 'btn-default');
               selectElem.setAttribute('data-selected-text-format' , 'count > 3');
               selectElem.setAttribute('data-live-search','true');
               selectElem.setAttribute('ng-model' , model);
               selectElem.setAttribute('ng-change' , "getData('"+model+"',item,'"+metaData.entityName+"','"+metaData.moduleName+"',"+(index+1)+",'"+modelpath+"')");
               divElem_1.appendChild(selectElem);  
               if(($scope.windowType=="view")||
                        ((angular.isDefined(field) &&angular.isDefined(field.updatable) && field.updatable==false)&&($scope.windowType!='new')&&($scope.innerWindowType==false))){
                    selectElem.setAttribute('disabled' , 'true');                    
                }
              selectElem.setAttribute('ng-options' , "item as item.designation for item in dataCache."+key);
              var optionElem = document.createElement('option');
              optionElem.setAttribute('value' , '');
              optionElem.appendChild(document.createTextNode('Please select option'));
              selectElem.appendChild(optionElem);
              //Desactiver la creation
              
                var spanElem = document.createElement('span');
                spanElem.setAttribute('class' , 'input-group-btn');
                divElem_1.appendChild(spanElem);
                var buttonElem = document.createElement('button');
                buttonElem.setAttribute('class' , 'btn btn-default btn-sm my-group-button');
                buttonElem.setAttribute('ng-click' , "editDialogBuilder('"+model+"',null,'new_entity','"+metaData.entityName+"','"+metaData.moduleName+"',"+(index+1)+",'"+modelpath+"')");
                buttonElem.setAttribute('data-toggle' , "modal");
//                buttonElem.setAttribute('data-target' , '#myModal');
                var nextIndex = index+1;   
                if(nextIndex==1){
                    buttonElem.setAttribute('data-target', '#myModal');   
                }else if( nextIndex==2){
                    buttonElem.setAttribute('data-target', '#globalModal');
                }else if( nextIndex==3){
                    buttonElem.setAttribute('data-target', '#myModal1');
                }else if( nextIndex==4){
                    buttonElem.setAttribute('data-target', '#myModal2');
                }//end if(index==1)           
                spanElem.appendChild(buttonElem);
                
                var spanElem_1 = document.createElement('span');
                spanElem_1.setAttribute('class','glyphicon glyphicon-plus');
                spanElem_1.setAttribute('aria-hidden' , 'true');
                spanElem_1.setAttribute('style' , 'color:blue');
                buttonElem.appendChild(spanElem_1);
//                console.log("ManyToMany =================== "+angular.toJson(field));
                if(($scope.windowType=="view"||metaData.createonfield==false)||((field.updatable==false)&&($scope.windowType!='new')&&($scope.innerWindowType==false))){
                    buttonElem.setAttribute('disabled' , 'disabled');
                }           
             return divElem;
        };
 
        /**
          Construction du chemin de la metadata
          @metaData : 
        **/
        $scope.getMetaDataPath = function(metaData){
             //console.log('Cas des groupes' +$scope.metaData.groups);
             var path = 'metaData';             

             if(($scope.metaData.entityName==metaData.entityName)){
                  return path;
             }else{
                 var subPath = '';
                 //Recherche parmi les columns
                 if(angular.isDefined($scope.metaData.columns)&&($scope.metaData.columns.length>0)){
                      for(var i=0 ; i< $scope.metaData.columns.length;i++){
                           if(($scope.metaData.columns[i].type=='array')||($scope.metaData.columns[i].type=='object')){
                                 if($scope.metaData.columns[i].metaData.entityName==metaData.entityName){
                                   // console.log(metaData.entityName+" ===== "+$scope.metaData.columns[i].metaData.entityName);
                                      subPath = 'metaData.columns['+i+'].metaData';
                                      return subPath;
                                 }//end if
                           }//end if
                      }//end for
                 }// end if

                 //Recherche dans les groupes
                 if(angular.isDefined($scope.metaData.groups)&&($scope.metaData.groups.length>0)){
                      
                      for(var i=0 ; $scope.metaData.groups.length ; i++){                             
                          //Cas des metaArray
                          if($scope.metaData.groups[i]&&$scope.metaData.groups[i].metaArray){
                             if($scope.metaData.groups[i].metaArray.metaData.entityName==metaData.entityName){
                                    return 'metaData.groups['+i+'].metaArray.metaData';
                             }
                         }//end if($scope.metaData.groups[i]&&$scope.metaData.groups[i].metaArray)
                          //Cas des columns
                         if($scope.metaData.groups[i]&&$scope.metaData.groups[i].columns&&($scope.metaData.groups[i].columns.length>0)){
                               for(var j=0 ; j< $scope.metaData.groups[i].columns.length;j++){
                                   if(($scope.metaData.groups[i].columns[j].type=='array')||($scope.metaData.groups[i].columns[j].type=='object')){                                       
                                       //  console.log($scope.metaData.groups[i].columns[j].type+" =====  entiyName :"+$scope.metaData.groups[i].columns[j].metaData.entityName+" == metaData.entityName : "+metaData.entityName);
                                        if($scope.metaData.groups[i].columns[j].metaData.entityName!=metaData.entityName){                                              
                                              subPath = 'metaData.groups['+i+'].columns['+j+'].metaData';
                                              return subPath;
                                         }//end if

                                   }//end if
                              }//end for
                         }
                      }
                 }
             }
        };

        /**
            Creation d'un composant table avec possibilte ajout modif suppression et consultation
            model : field to map
            labelText : the Label of the input
            entityName : name of the entity,
            metaData : MetaData which describe de object
        **/
        $scope.oneToManyComponent = function(model , labelText , entityName ,metaData,field,index,modelpath){
            
//             $scope.currentMetaDataPath = $scope.getMetaDataPath(metaData);
//              console.log("$scope.oneToManyComponent = === "+model+"==="+labelText+" == "+"=== "+entityName+" == "+index); 
             var divElem = document.createElement('div');
             divElem.setAttribute('class' , 'table-responsive');
             //Ajout d'un champs input de type hidden pour stocker le model
             var inputHidden = document.createElement('input');
             ///inputHidden.setAttribute('type' , 'hidden')
             //inputHidden.setAttribute('id',)
             var tableElem = document.createElement('table');
             tableElem.setAttribute('class','table table-sm table-striped table-bordered table-hover table-condensed');
             divElem.appendChild(tableElem);
             var theadElem = document.createElement('thead');
             tableElem.appendChild(theadElem);
             var trElem = document.createElement('tr');
             trElem.setAttribute('class' , 'table-header');
             theadElem.appendChild(trElem);
             //console.log(metaData);
             var columnNumber = 0 ;
             var fieldnames = new Array();
             for(var i = 0 ; i < metaData.columns.length;i++){
                 if(angular.isDefined(metaData.columns[i].search) && metaData.columns[i].search){
                   var thElem = document.createElement('th');
                   thElem.appendChild(document.createTextNode(metaData.columns[i].fieldLabel));
                   trElem.appendChild(thElem);
                   fieldnames.push(metaData.columns[i].fieldName);
                   columnNumber++;
                 }
             }
             //Traitement  des champs des groupes
             if(metaData.groups&&metaData.groups.length>0){
                 for(var i=0;i<metaData.groups.length;i++){
                     //Cas des columns
                     if(metaData.groups[i].columns&&metaData.groups[i].columns.length>0){
                          for(var j = 0 ; j < metaData.groups[i].columns.length;j++){
                            if(angular.isDefined(metaData.groups[i].columns[j].search) && metaData.groups[i].columns[j].search){
                              var thElem = document.createElement('th');
                              thElem.appendChild(document.createTextNode(metaData.groups[i].columns[j].fieldLabel));
                              trElem.appendChild(thElem);
                              fieldnames.push(metaData.groups[i].columns[j].fieldName);
                              columnNumber++;
                            }//end  if(angular.isDefined(metaData.groups[i].columns[j].search) && metaData.groups[i].columns[j].search)
                        }//end for(var j = 0 ; j < metaData.groups[i].columns.length;j++)
                     }//end if(metaData.groups[i].columns&&metaData.groups[i].columns.length>0)                     
                 }//end for(var i=0;i<metaData.groups.length;i++)
             }
             //alert(columnNumber);
             var nextIndex = index +1 ;
             //Creation du corps du tableau
             var tbodyElem = document.createElement('tbody');
             tableElem.appendChild(tbodyElem);
              if(metaData.createonfield==true){  
                    //Creation de la ligne des actions
                    var trElem = document.createElement('tr');
                    tbodyElem.appendChild(trElem);
                    var tdElem = document.createElement('td');
                    trElem.appendChild(tdElem);
                    tdElem.setAttribute("colspan" , columnNumber);
                    var aElem = document.createElement('a');
                    tdElem.appendChild(aElem);
                    aElem.setAttribute('href' , '#');
                    //Diseable si ajout impossible               
                   aElem.setAttribute('disabled' , 'disabled');
                   aElem.setAttribute('data-toggle' , "modal");
                   if(nextIndex==1){
                       aElem.setAttribute('data-target', '#myModal');   
                   }else if( nextIndex==2){
                       aElem.setAttribute('data-target', '#globalModal');
                   }else if( nextIndex==3){
                       aElem.setAttribute('data-target', '#myModal1');
                   }else if( nextIndex==4){
                       aElem.setAttribute('data-target', '#myModal2');
                   }//end if(index==1)           
                   aElem.setAttribute('ng-click' , "editDialogBuilder('"+model+"',null,'new',null,null,+"+(nextIndex)+",'"+modelpath+"')");                      
                   aElem.appendChild(document.createTextNode("Ajouter un element"));
                   if($scope.windowType=="view"){
                       aElem.setAttribute('disabled' , 'disabled');                  
                   }            
               }//end if(metaData.createonfield==true)
             
             //Construction du corps du tableau
             trElem = document.createElement('tr');
             trElem.setAttribute('style' ,"cursor: pointer;");
             tbodyElem.appendChild(trElem);
             trElem.setAttribute('ng-repeat' , 'item in '+model);
             //alert(metaData.columns.length);
             for(var i=0 ; i< metaData.columns.length;i++){
                if(angular.isDefined(metaData.columns[i].search) && metaData.columns[i].search){
                     var tdElem = document.createElement('td');
                     if(metaData.columns[i].type!='array'&& metaData.columns[i].type!='object'){
                         tdElem.appendChild(document.createTextNode('{{item.'+metaData.columns[i].fieldName+'}}'));
                         if(metaData.columns[i].type=='number'){
                            tdElem.setAttribute('class','text-right');
                         }
                     }else if(metaData.columns[i].type=='object'){
                          //console.log("$scope.oneToManyComponent ============= "+"{{item."+metaData.columns[i].fieldName+"['designation']}}");
                          tdElem.appendChild(document.createTextNode("{{item."+metaData.columns[i].fieldName+"['designation']}}"));
                     }
                     tdElem.setAttribute('data-toggle' , "modal");
                     //tdElem.setAttribute('data-target' , '#myModal');
                     if(nextIndex==1){
                        tdElem.setAttribute('data-target', '#myModal');   
                     }else if( nextIndex==2){
                        tdElem.setAttribute('data-target', '#globalModal');
                     }else if( nextIndex==3){
                        tdElem.setAttribute('data-target', '#myModal1');
                     }else if( nextIndex==4){
                        tdElem.setAttribute('data-target', '#myModal2');
                     }//end if(index==1)           
                     tdElem.setAttribute('ng-click',"editDialogBuilder('"+model+"' ,item,'update',null,null,"+(nextIndex)+",'"+modelpath+"')");    
                     trElem.appendChild(tdElem);
                 }
             }
             //Traitement  des champs des groupes
             if(metaData.groups&&metaData.groups.length>0){
                 for(var i=0;i<metaData.groups.length;i++){
                     //Cas des columns
                     if(metaData.groups[i].columns&&metaData.groups[i].columns.length>0){
                          for(var j = 0 ; j < metaData.groups[i].columns.length;j++){
                            if(angular.isDefined(metaData.groups[i].columns[j].search) && metaData.groups[i].columns[j].search){
                                var tdElem = document.createElement('td');
                                if(metaData.groups[i].columns[j].type!='array'&& metaData.groups[i].columns[j].type!='object'){
                                    tdElem.appendChild(document.createTextNode('{{item.'+metaData.groups[i].columns[j].fieldName+'}}'));
                                    if(metaData.groups[i].columns[j].type=='number'){
                                        tdElem.setAttribute('class','text-right');
                                    }
                                }else if(metaData.groups[i].columns[j].type=='object'){
                                    tdElem.appendChild(document.createTextNode("{{item."+metaData.groups[i].columns[j].fieldName+"['designation']}}"));
                                }
                                tdElem.setAttribute('data-toggle' , "modal");
                                 if(nextIndex==1){
                                    tdElem.setAttribute('data-target', '#myModal');   
                                 }else if( nextIndex==2){
                                    tdElem.setAttribute('data-target', '#globalModal');
                                 }else if( nextIndex==3){
                                    tdElem.setAttribute('data-target', '#myModal1');
                                 }else if( nextIndex==4){
                                    tdElem.setAttribute('data-target', '#myModal2');
                                 }//end if(index==1)           
                                tdElem.setAttribute('ng-click',"editDialogBuilder('"+model+"' ,item,'update',null,null,"+(nextIndex)+",'"+modelpath+"')");    
                                trElem.appendChild(tdElem);
                            }//end if(angular.isDefined(metaData.groups[i].columns[j].search) && metaData.groups[i].columns[j].search)
                        }//end for(var j = 0 ; j < metaData.groups[i].columns.length;j++)
                     }//end if(metaData.groups[i].columns&&metaData.groups[i].columns.length>0)                     
                 }//end for(var i=0;i<metaData.groups.length;i++)
             }
             var key = commonsTools.keygenerator(modelpath);
            //construction du pied de tableau            
             if(field.customfooter==false){
                var sources = model.split(".");    
                $scope.dataCache[key+"foot"] = null;
                var footerElem = document.createElement('tfoot');
                footerElem.setAttribute("id",key);
                if(fieldnames.length>0){
                    for(var i=0 ;  i<fieldnames.length ; i++){
                        var thelem = document.createElement('th');
                        footerElem.appendChild(thelem);
                        var data = $scope.currentObject;
                        if(sources[0]!='currentObject'){
                            data = $scope.dataCache[""+key];
                        }//end if(model!='currentObject')
//                        console.log("$scope.oneToManyComponent &&&&&& === "+fieldnames[i]+"===="+field.fieldName+"===="+angular.toJson(data));
                        if(data){
                            var total = commonsTools.sumTableField(fieldnames[i],data[field.fieldName]);
                            if(total){
                                thelem.appendChild(document.createTextNode(total));
                                thelem.setAttribute('class','text-right');
                            }//end if(total)
                        }//end if(data)
                    }//end for(var i=0 ;  i<fieldnames.length ; i++)
                    tableElem.appendChild(footerElem);
                }//end if(fieldnames.length>0)
            }else{
                var sources = model.split(".");   
                $scope.dataCache[key+"foot"] = field.footerScript;
                var data = $scope.currentObject;
                if(sources[0]!='currentObject'){
                     data = $scope.dataCache[""+key];//data = $scope.temporalData;
                }//end if(model!='currentObject')
                if(data){
                    var piedElem = commonsTools.tableFooterBuilder(field.footerScript,data[field.fieldName],sources[1],key);  
                    tableElem.appendChild(piedElem);
                }//end if(data)
            }//end if(field.customfooter)
            if(metaData.createonfield==true){
             //Suppression
                tdElem = document.createElement('td');
                trElem.appendChild(tdElem);
                aElem = document.createElement('a');
                aElem.setAttribute('href' , '#');
                aElem.setAttribute('ng-click' , "removeFromTable('"+model+"',item,'"+modelpath+"')"); 
                tdElem.appendChild(aElem);
                spanElem = document.createElement('span');
                spanElem.setAttribute('class' , 'glyphicon glyphicon-trash');
                spanElem.setAttribute('aria-hidden' , 'true');
                aElem.appendChild(spanElem);
                if($scope.windowType=="view"){
                     aElem.setAttribute('disabled' , 'disabled');                  
                 }            
            }
                       
             /*alert($scope.currentObject.actions);
             var divElem0 = document.createElement('div');
             divElem0.appendChild(divElem);
             alert(divElem0.innerHTML);*/
             return divElem;
        };
        
          /**
            Creation d'un composant table avec possibilte ajout modif suppression et consultation
            model : field to map
            labelText : the Label of the input
            entityName : name of the entity,
            metaData : MetaData which describe de object
        **/
        $scope.manyToManyListComponent = function(model , labelText , entityName ,metaData,field,index,modelpath){
//              console.log("$scope.manyToManyListComponent =before === "+model+"==="+labelText+" == "+"=== "+entityName+" == "+index+" === "+modelpath); 
//             $scope.currentMetaDataPath = $scope.getMetaDataPath(metaData);
             var divElem = document.createElement('div');
             divElem.setAttribute('class' , 'table-responsive');
             //Ajout d'un champs input de type hidden pour stocker le model
             var inputHidden = document.createElement('input');
             ///inputHidden.setAttribute('type' , 'hidden')
             //inputHidden.setAttribute('id',)
             var tableElem = document.createElement('table');
             tableElem.setAttribute('class','table table-sm table-striped table-bordered table-hover table-condensed');
             divElem.appendChild(tableElem);
             var theadElem = document.createElement('thead');
             tableElem.appendChild(theadElem);
             var trElem = document.createElement('tr');
             trElem.setAttribute('class' , 'table-header');
             theadElem.appendChild(trElem);
             //console.log(metaData);
             var columnNumber = 0 ;
             var fieldnames = new Array();
             for(var i = 0 ; i < metaData.columns.length;i++){
                 if(angular.isDefined(metaData.columns[i].search) && metaData.columns[i].search){
                   var thElem = document.createElement('th');
                   thElem.appendChild(document.createTextNode(metaData.columns[i].fieldLabel));
                   trElem.appendChild(thElem);
                   fieldnames.push(metaData.columns[i].fieldName);
                   columnNumber++;
                 }
             }
             //Traitement  des champs des groupes
             if(metaData.groups&&metaData.groups.length>0){
                 for(var i=0;i<metaData.groups.length;i++){
                     //Cas des columns
                     if(metaData.groups[i].columns&&metaData.groups[i].columns.length>0){
                          for(var j = 0 ; j < metaData.groups[i].columns.length;j++){
                            if(angular.isDefined(metaData.groups[i].columns[j].search) && metaData.groups[i].columns[j].search){
                              var thElem = document.createElement('th');
                              thElem.appendChild(document.createTextNode(metaData.groups[i].columns[j].fieldLabel));
                              trElem.appendChild(thElem);
                              fieldnames.push(metaData.groups[i].columns[j].fieldName);
                              columnNumber++;
                            }//end if(angular.isDefined(metaData.groups[i].columns[j].search)
                        }//end for(var j = 0 ; j < metaData.groups[i].columns.length;j++)
                     }//end if(metaData.groups[i].columns&&metaData.groups[i].columns.length>0){
                     
                 }//end for(var i=0;i<metaData.groups.length;i++)
             }//end if(metaData.groups&&metaData.groups.length>0)
             //alert(columnNumber);
             var endIndex = index+1;
             //Creation du corps du tableau
             var tbodyElem = document.createElement('tbody');
             tableElem.appendChild(tbodyElem);
              /*if(metaData.createonfield==true)*/{  
                    //Creation de la ligne des actions
                    var trElem = document.createElement('tr');
                    tbodyElem.appendChild(trElem);
                    var tdElem = document.createElement('td');
                    trElem.appendChild(tdElem);
                    tdElem.setAttribute("colspan" , columnNumber);
                    var aElem = document.createElement('a');
                    tdElem.appendChild(aElem);
                    aElem.setAttribute('href' , '#');
                    //Diseable si ajout impossible     
                    var modalID = "";
                    if(endIndex==1){
                        modalID="myModal";
                    }else if(endIndex==2){
                        modalID="globalModal";
                    }else if(endIndex==3){
                        modalID="myModal1";
                    }else if(endIndex==4){
                        modalID="myModal2";
                    }//end if(endIndex==1)
                   aElem.setAttribute('disabled' , 'disabled');
                   aElem.setAttribute('data-toggle' , "modal");
                   aElem.setAttribute('data-target' , '#'+modalID);
//                     if(index==1){
//                        aElem.setAttribute('data-target', '#myModal');   
//                     }else if( index==2){
//                        aElem.setAttribute('data-target', '#globalModal');
//                     }else if( index==3){
//                        aElem.setAttribute('data-target', '#myModal1');
//                     }else if( index==4){
//                        aElem.setAttribute('data-target', '#myModal2');
//                     }//end if(index==1)           
                   aElem.setAttribute('ng-click' , "listDialogBuilder('"+model+"',"+(index+1)+",'"+modelpath+"')");                      
                   aElem.appendChild(document.createTextNode("Ajouter un element"));
                   if($scope.windowType=="view"){
                       aElem.setAttribute('disabled' , 'disabled');                  
                   }            
               }
             
             //Construction du corps du tableau
             trElem = document.createElement('tr');
             trElem.setAttribute('style' ,"cursor: pointer;");
             tbodyElem.appendChild(trElem);
             trElem.setAttribute('ng-repeat' , 'item in '+model);
             //alert(metaData.columns.length);
             for(var i=0 ; i< metaData.columns.length;i++){
                if(angular.isDefined(metaData.columns[i].search) && metaData.columns[i].search){
                     var tdElem = document.createElement('td');
                     if(metaData.columns[i].type!='array'&& metaData.columns[i].type!='object'){
                         tdElem.appendChild(document.createTextNode('{{item.'+metaData.columns[i].fieldName+'}}'));
                         if(metaData.columns[i].type=='number'){
                            tdElem.setAttribute('class','text-right');
                         }
                     }else if(metaData.columns[i].type=='object'){
                          //console.log("$scope.oneToManyComponent ============= "+"{{item."+metaData.columns[i].fieldName+"['designation']}}");
                          tdElem.appendChild(document.createTextNode("{{item."+metaData.columns[i].fieldName+"['designation']}}"));
                     }
                     tdElem.setAttribute('data-toggle' , "modal");
                     tdElem.setAttribute('data-target' , '#'+modalID);
                     tdElem.setAttribute('ng-click',"editDialogBuilder('"+model+"' ,item,'update',null,null,"+(index+1)+",'"+modelpath+"')");    
                     trElem.appendChild(tdElem);
                 }
             }
             //Traitement  des champs des groupes
             if(metaData.groups&&metaData.groups.length>0){
                 for(var i=0;i<metaData.groups.length;i++){
                     //Cas des columns
                     if(metaData.groups[i].columns&&metaData.groups[i].columns.length>0){
                          for(var j = 0 ; j < metaData.groups[i].columns.length;j++){
                            if(angular.isDefined(metaData.groups[i].columns[j].search) && metaData.groups[i].columns[j].search){
                                var tdElem = document.createElement('td');
                                if(metaData.groups[i].columns[j].type!='array'&& metaData.groups[i].columns[j].type!='object'){
                                    tdElem.appendChild(document.createTextNode('{{item.'+metaData.groups[i].columns[j].fieldName+'}}'));
                                    if(metaData.groups[i].columns[j].type=='number'){
                                        tdElem.setAttribute('class','text-right');
                                     }
                                }else if(metaData.groups[i].columns[j].type=='object'){
                                    tdElem.appendChild(document.createTextNode("{{item."+metaData.groups[i].columns[j].fieldName+"['designation']}}"));
                                }
                                tdElem.setAttribute('data-toggle' , "modal");
                                tdElem.setAttribute('data-target' , '#'+modalID);
                                //tdElem.setAttribute('ng-click',"editDialogBuilder('"+model+"' ,item,'update')");    
                                trElem.appendChild(tdElem);
                            }
                        }
                     }
                     
                 }
             }
             var key = commonsTools.keygenerator(modelpath);
             //Building table footer
             if(field.customfooter==false){
                var sources = model.split(".");    
                $scope.dataCache[key+"foot"] = null;
                var footerElem = document.createElement('tfoot');
                footerElem.setAttribute("id",key);
                if(fieldnames.length>0){
                    for(var i=0 ;  i<fieldnames.length ; i++){
                        var thelem = document.createElement('th');
                        footerElem.appendChild(thelem);
                        var data = $scope.currentObject;
                        if(sources[0]!='currentObject'){
                            data = $scope.dataCache[""+key];
                        }//end if(model!='currentObject')
//                        console.log("$scope.oneToManyComponent &&&&&& === "+fieldnames[i]+"===="+field.fieldName+"===="+angular.toJson(data));
                        if(data){
                            var total = commonsTools.sumTableField(fieldnames[i],data[field.fieldName]);
                            if(total){
                                thelem.appendChild(document.createTextNode(total));
                                thelem.setAttribute('class','text-right');
                            }//end if(total)
                        }//end if(data)
                    }//end for(var i=0 ;  i<fieldnames.length ; i++)
                    tableElem.appendChild(footerElem);
                }//end if(fieldnames.length>0)
            }else{
                var sources = model.split(".");   
                $scope.dataCache[key+"foot"] = field.footerScript;
                var data = $scope.currentObject;
                if(sources[0]!='currentObject'){
                     data = $scope.dataCache[""+key];//data = $scope.temporalData;
                }//end if(model!='currentObject')
                if(data){
                    var piedElem = commonsTools.tableFooterBuilder(field.footerScript,data[field.fieldName],sources[1],key);  
                    tableElem.appendChild(piedElem);
                }//end if(data)
            }//end if(field.customfooter)
            /*if(metaData.createonfield==true)*/{
             //Suppression
                tdElem = document.createElement('td');
                trElem.appendChild(tdElem);
                aElem = document.createElement('a');
                aElem.setAttribute('href' , '#');
                aElem.setAttribute('ng-click' , "removeFromTable('"+model+"',item,'"+modelpath+"')"); 
                tdElem.appendChild(aElem);
                spanElem = document.createElement('span');
                spanElem.setAttribute('class' , 'glyphicon glyphicon-trash');
                spanElem.setAttribute('aria-hidden' , 'true');
                aElem.appendChild(spanElem);
                if($scope.windowType=="view"){
                     aElem.setAttribute('disabled' , 'disabled');                  
                 }            
            }
//             alert($scope.currentObject.actions);
//             var divElem0 = document.createElement('div');
//             divElem0.appendChild(divElem);
//             alert(divElem0.innerHTML);
             return divElem;
        };
       
        /**
         * Build t edit formhe header of th
         * @param {type} model
         * @param {type} metaData
         * @param {type} windowType
         * @returns {undefined}
         */
        $scope.editPanelHeader = function(model , metaData,index){
            if(metaData && metaData.header && metaData.header.length>0){
                var divElem = document.createElement('div');
                divElem.setAttribute('class','panel panel-default col-sm-12  col-md-12');
                divElem.setAttribute('style','margin-top: 8px;margin-bottom: 0px;border-top: solid 1px #a8a8a8;background-color: #f0eeee;background-image: linear-gradient(to bottom, #fcfcfc, #dedede);');
                var header = document.createElement('div');
                divElem.appendChild(header);
                header.setAttribute('class','collapse navbar-collapse');
                header.setAttribute('style','position:center;');
//                var navElem = document.createElement('nav');
//                navElem.setAttribute('class','navbar navbar-sm');
//                header.appendChild(navElem);
                var ulElem = document.createElement('ul');
                ulElem.setAttribute('class','nav navbar-nav navbar-left left-menu-bar');
                header.appendChild(ulElem);
                var staturbar = null;
                for(var i=0 ; i<metaData.header.length;i++){
                    if(metaData.header[i].type!='workflow'){
                      var liElem =document.createElement('li');
                      ulElem.appendChild(liElem);
                      var aElem = document.createElement('button');
                      ulElem.appendChild(aElem);
                      var clasElem = 'btn btn-default  btn-sm';
                      if(metaData.header[i].pattern&&metaData.header[i].pattern.length>0){
                          clasElem = "'"+metaData.header[i].pattern+" btn-sm'";
                      }
                      //Verifier si l'etat est autorise
                      if(metaData.header[i].states && metaData.header[i].states.length>0){
                          aElem.setAttribute('ng-click',"buttonAction("+metaData.header[i].value+" , '"+metaData.header[i].target+"','"+angular.toJson(metaData.header[i].states)+"','"+index+"')");
                      }else{
                          aElem.setAttribute('ng-click',"buttonAction("+metaData.header[i].value+" , '"+metaData.header[i].target+"',null,'"+index+"')");
                      }//end if(metaData.header[i].states && metaData.header[i].states.length>0)
                      aElem.setAttribute('class',clasElem);
                      aElem.setAttribute('style','margin-right: 5px;');
                      aElem.innerHTML=metaData.header[i].fieldLabel; 
                   }else{
                      staturbar = metaData.header[i]; 
                   }//end if(metaData.header[i].type!='workflow'){
                }//end for(var i=0 ; i<metaData.header.length;i++)
                
                //Creation de workflow
                if(staturbar!=null){
                    if(metaData.states && metaData.states.length>0){
                        var statusElem = document.createElement('ul');
                        statusElem.setAttribute("class","nav navbar-nav navbar-right right-menu-bar");
                        for(var i=0 ; i<metaData.states.length;i++){
                            var liElem = document.createElement('li');
                            if($scope.currentObject && $scope.currentObject[staturbar.fieldName]
                                    && $scope.currentObject[staturbar.fieldName]==metaData.states[i].code){
                               liElem.setAttribute('style','display: inline-block;background-color:#337ab7;height: 30px;font-size: 85%;border-top-right-radius:  50%;border-bottom-right-radius: 50%;padding-left: 5px;padding-right: 5px;');
                           }else{
                               liElem.setAttribute('style','display: inline-block;background-color:#d9d9d9;height: 30px;font-size: 85%;border-top-right-radius:  50%;border-bottom-right-radius: 50%;padding-left: 5px;padding-right: 5px;');
                           }
                            statusElem.appendChild(liElem);
                            var spanElem = document.createElement('span');
                            liElem.appendChild(spanElem);
                            spanElem.setAttribute('class','workflow');
                             if($scope.currentObject && $scope.currentObject[staturbar.fieldName]
                                    && $scope.currentObject[staturbar.fieldName]==metaData.states[i].code){
                                 spanElem.setAttribute('style','color: white;');
                             }else{
                                 spanElem.setAttribute('style','color: black;');
                             }
                            spanElem.appendChild(document.createTextNode(metaData.states[i].intitule));
                        }//end for(var i=0 ; i<metaData.states.length;i++)
                        header.appendChild(statusElem);
                    }//end if(metaData.states && metaData.states.length>0)
                }//end if(staturbar!=null)
                return divElem;
            }
            
        };

       /**
        * 
        * @param {type} model
        * @param {type} metaData
        * @param {type} windowType
        * @param {type} index
        * @param {type} modelpath
        * @returns {Element}
        */
        $scope.editPanelComponent = function(model , metaData , windowType,index,modelpath){   
//            console.log("$scope.editPanelComponent ==== "+index);
              var divElem = null ;
             if(angular.isDefined(metaData)){
                  divElem = document.createElement('div');
                  divElem.setAttribute('class' , 'panel-body');
                  divElem.setAttribute("style","height:100%;");
                  var formElem = document.createElement('form');
                  divElem.appendChild(formElem);
                  formElem.setAttribute('role' , 'form');
                  formElem.setAttribute('class' , 'form-horizontal');
                  formElem.setAttribute('name' , metaData.entityName);
                  formElem.setAttribute("novalidate" , "novalidate");
                  formElem.setAttribute("style","margin-left:15px;");
//                  console.log("For editPanelComponent 00i init ::::: "+model+" === Panel edit");
                  //Construction des champs
                  if(angular.isDefined(metaData.columns) && (metaData.columns.length>0)){
                     //$scope.panelComponent = function(model , fields , entityName)
                       $scope.panelComponent(model , metaData.columns , metaData.entityName , formElem,null,index ,modelpath);                      
                  }
//                  console.log("For editPanelComponent ::::: "+model+" === Panel edit");
                 //Construction des tabpane
                 if(angular.isDefined(metaData.groups) && (metaData.groups.length > 0)){
                      //$scope.tabPaneComponent = function(model , labelText , entityName ,groups )
                      metaData.groups = $filter('orderBy')(metaData.groups,'groupName',false);
                      var divElem_2 = $scope.tabPaneComponent(model , metaData.entityName , metaData.entityName , metaData.groups,index ,modelpath);

                      if(angular.isDefined(divElem_2) && (divElem_2 != null)){
                          formElem.appendChild(divElem_2);
                      }
                 }
//                 console.log("For editPanelComponent ::::: "+model+" === tabPaneComponent");

//                 var divElem0 = document.createElement('div');
//                 divElem0.appendChild(divElem);
//                 console.log(divElem0.innerHTML);

                 return formElem;
 
             }
           
        };
        
       /**
        * 
        * @param {type} model
        * @param {type} metaData
        * @param {type} windowType
        * @returns {Element}
        */
        $scope.editReportPanelComponent = function(metaData){
              
              var divElem = null ;
             if(angular.isDefined(metaData)){
                  divElem = document.createElement('div');
                  divElem.setAttribute('class' , 'panel-body'); 
                  //Construction des champs
                  if(angular.isDefined(metaData.columns) && (metaData.columns.length>0)){
                     //$scope.panelComponent = function(model , fields , entityName)
                       $scope.panelReportComponent($scope.currentObject , metaData.columns  , divElem );                      
                  }
                  //console.log("For editPanelComponent ::::: "+model+" === Panel edit");
                 //Construction des tabpane
                 if(angular.isDefined(metaData.groups) && (metaData.groups.length > 0)){
                      //$scope.tabPaneComponent = function(model , labelText , entityName ,groups )
                      var divElem_2 = $scope.tabPaneReportComponent($scope.currentObject , metaData.groups);

                      if(angular.isDefined(divElem_2) && (divElem_2 != null)){
                          divElem.appendChild(divElem_2);
                      }
                 }
                 //console.log("For editPanelComponent ::::: "+model+" === tabPaneComponent");

                 var divElem0 = document.createElement('div');
                 divElem0.appendChild(divElem);
//                 console.log(divElem0.innerHTML);
                 document.getElementById("report_template").innerHTML = divElem0.innerHTML;
                 return divElem;
 
             }
           
        };

/**
 * 
 * @param {type} model
 * @param {type} fields
 * @param {type} entityName
 * @param {type} viewElem
 * @param {type} metaDataName
 * @returns {@var;viewElem}
 */
      $scope.panelReportComponent = function(model , fields , viewElem ){
              
            if(angular.isDefined(viewElem) && (viewElem!=null)){  
              var divElem = viewElem;
              var row  = document.createElement("div");
              row.setAttribute("class","row");
//              var spanElem_1 = document.createElement('div') ;
//              spanElem_1.setAttribute('class' , 'col-md-6 text-center');
//              divElem.appendChild(spanElem_1);
//              var spanElem_2 = document.createElement('div');
//              spanElem_2.setAttribute('class' , 'col-md-6 text-center');
//              divElem.appendChild(spanElem_2);
              if(fields.length % 2 ==1){
                //fields.push({type:"emailpty" , search:true ,fieldName:" " , fieldLabel:" "})
              }
              if(angular.isDefined(fields)){
                  //Sort of array
                  fields = $filter('orderBy')(fields,'sequence',false);
                  //console.log("$scope.panelComponent :::: "+model+" == "+fields[i]+" == "+entityName+" == "+metaDataName+" == "+angular.toJson(fields[i]));
                  if(fields.length==1){
                      var field = fields[0];
                      var key = field.fieldName ;
                      var col6 = document.createElement("div");
                      col6.setAttribute("class","col-md-6 text-center");
                      var span1 = document.createElement("span");
                      var strong = document.createElement("strong");
                      strong.appendChild(document.createTextNode(field.fieldLabel));
                      span1.appendChild(strong);
                      col6.appendChild(span1);
                      var span2 = document.createElement("span");
                      col6.appendChild($scope.lineTextBuilder(fields[0] , model[key]));
                      row.appendChild(col6);
                      return row;
                  }
                  var start = 0 ;
//                  if(fields[0].type=='image'){
//                      divElem.appendChild($scope.getIhmComponent(model , fields[0] , entityName , metaDataName));
//                      start = 1 ;
//                  }
                  for(var i = start ; i<fields.length ; i++){
                      if(fields[i].type != 'array'|| fields[i].type!='textarea'|| fields[i].type!='richeditor'||fields[i].target=='many-to-many'){
                           var field = fields[i];
                            var key = field.fieldName ;
                            var col6 = document.createElement("div");
                            if(i%2==0){
                                col6.setAttribute("class","col-md-6");
                            }else{
                                col6.setAttribute("class","col-md-6 text-center");
                            }
                            var span1 = document.createElement("span");
                            var strong = document.createElement("strong");
                            strong.appendChild(document.createTextNode(field.fieldLabel));
                            span1.appendChild(strong);
                            col6.appendChild(span1);
                            var br = document.createElement("br");
                            col6.appendChild(br);
                            var span2 = document.createElement("span");
                            var value = model[key];
                            if(angular.isObject(model[key])){
                               span2.appendChild(document.createTextNode(value['designation']));
                            }else if(model[key]){
                               span2.appendChild(document.createTextNode(value)); 
                            }else{
                                span2.appendChild(document.createTextNode(''));
                            }
                            col6.appendChild(span2);
                            row.appendChild(col6);
                        }else{
                            
                           divElem.appendChild($scope.lineTextBuilder(fields[i] , model[key]));
                           
                        }
                  }
              }
              divElem.appendChild(row)
             /* var divElem0 = document.createElement('div');
             divElem0.appendChild(divElem);
             console.log(divElem0.innerHTML);*/
              return divElem;
            }
        };
        /**
             Create component tabPane
             model : field to map
            labelText : the Label of the input
            entityName : name of the entity,
            metaData : MetaData which describe de object
        **/
        $scope.tabPaneReportComponent = function(model,groups ){
            var divElem = document.createElement('div');
            document.createElement('div');
            for(var i=0;i<groups.length;i++){
                if(groups[i].metaArray){
                    var group = groups[i];
//                    console.log("$scope.tabPaneReportComponent == "+angular.toJson(group));
                    var key = group.metaArray['fieldName'];
                    var table = $scope.tableComponentReport(model[key],group.metaArray['metaData']);
                    divElem.appendChild(table);
                }//end if(groups[i].metaArray)
            }
             //divElem0.appendChild(divElem);
             //console.log(divElem0.innerHTML);

            return divElem;

        };
        
           /**
          * 
          * @param {type} model
          * @param {type} metaData
          * @returns {Element}
          */
       $scope.tableComponentReport = function(model,metaData){
//           console.log("$scope.tableComponentReport ========= "+metaData);
            var divresponsive = document.createElement("div");
            divresponsive.setAttribute("class","table-responsive");
             var tableElem = document.createElement('table');
//             tableElem.setAttribute('style','border:solid 1px;')
             divresponsive.appendChild(tableElem);     
             var columns = new Array();
             tableElem.setAttribute('class' , 'table  table-condensed');
             tableElem.setAttribute('style' , 'width: 100%;');
             tableElem.setAttribute('id' , 'table');
             //Creation du table header
             var theadElem = document.createElement('thead');
//             theadElem.setAttribute('style','display: table-header-group;vertical-align: middle; border-color: inherit;');
             tableElem.appendChild(theadElem);
             //Creation entete
             var  rheadElem = document.createElement('tr');
//             rheadElem.setAttribute('class' , 'small');
             theadElem.appendChild(rheadElem);
//             rheadElem.setAttribute('style','border:solid 5px;position: center;');
            //Traitement des champs columns
            if(metaData.columns){
                //Sort of array
                metaData.columns = $filter('orderBy')(metaData.columns,'colsequence',false);  
                var k =0;
                for(var i=0 ; i< metaData.columns.length;i++){
                  if(angular.isDefined(metaData.columns[i].search)
                            &&(metaData.columns[i].search==true)){
                      if(metaData.columns[i].type!='array'&&metaData.columns[i].type!='image'&&metaData.columns[i].type!='textarea'&&metaData.columns[i].type!='richeditor'){  
                            var thElem = document.createElement('th');
                            thElem.setAttribute("style","font-size: small;");
                            thElem.setAttribute("class","text-center small");
                            thElem.appendChild(document.createTextNode(metaData.columns[i].fieldLabel))  ;
                            columns[k]=metaData.columns[i].fieldName;
                            k++;
                            rheadElem.appendChild(thElem);
                       }//end if(metaData.columns[i].type!='array'
                     }//end if(angular.isDefined(metaData.columns[i].search)
                }
            }
            //Traitement des groups
            if(metaData.groups){
                //Cas des columns
                for(var i=0 ; i<metaData.groups.length;i++){
                    if(metaData.groups[i]&&metaData.groups[i].columns){
                        for(var j=0 ; j< metaData.groups[i].columns.length;j++){
                            if(angular.isDefined(metaData.groups[i].columns[j].search)
                                      &&(metaData.groups[i].columns[j].search==true)){
                                 if(metaData.groups[i].columns[j].type!='array'&&metaData.groups[i].columns[j].type!='image'
                                         &&metaData.groups[i].columns[j].type.type!='textarea'&&metaData.groups[i].columns[j].type.type!='richeditor'){   
                                    var thElem = document.createElement('th');
                                    thElem.setAttribute("style","font-size: small;");
                                    thElem.setAttribute("class","text-center small");
                                    thElem.appendChild(document.createTextNode(metaData.groups[i].columns[j].fieldLabel));
                                    columns[k]=metaData.groups[i].columns[j].fieldName;
                                     k++;
                                    rheadElem.appendChild(thElem);
                                }
                            }
                        }//end For
                    }//Fin traitement des colommes
                }
            }

            //Creation du corps du tableau
            var tbodyElem = document.createElement('tbody');
            tableElem.appendChild(tbodyElem);            
 
            for(var i=0 ; i< model.length;i++){
                    var data = model[i];
                    var rbodyElem = document.createElement('tr');   
                    //rbodyElem.setAttribute('class','small');
                    tbodyElem.appendChild(rbodyElem);
                    for(var j=0 ; j<columns.length;j++){ 
                        /*if(data[columns[j]])*/{   
                             var tdElem = document.createElement('td');
                            rbodyElem.appendChild(tdElem);
                            tdElem.setAttribute("style","font-size: small;");
                            tdElem.setAttribute("class","text-center small");
                            var col = data[columns[j]];
                            //console.log(columns[j]+"=============="+angular.toJson(col));                        
                            if(angular.isObject(col)){
                                tdElem.appendChild(document.createTextNode(col['designation']));
                            }else if(col){
                                 tdElem.appendChild(document.createTextNode(col));
//                                tdElem.innerHTML = col;
                                 if(angular.isNumber(col)){
                                     tdElem.setAttribute("class","text-right small");
                                 }//end if(angular.isNumber(col))
                            }else{
                                tdElem.appendChild(document.createTextNode(""));
                            }//end if(col['designation'])
                            //var tdElem = document.createElement('td');
                            //rbodyElem.appendChild(tdElem);
                        }
                       
                    }
                    
             }//construction du tableau        

//            var divElem = document.createElement('div');
//            divElem.appendChild(divresponsive);
//            console.log(divElem.innerHTML);
            return divresponsive;
         };

        
        /**
             Fonction qui renvoie un composant en fonction de la valeur d'un champs
             @model: model
             @field : metacolumn 
             entityName: nom de l'entite
         **/
         $scope.getIhmComponent = function(model , field , entityName , metaDataName,index,modelpath){
//               console.log("$scope.getIhmComponent = function(model , field , entityName , metaDataName) === "+field.fieldName+" ===== "+field.target+" ===== "+field.type+" == "+index);
                    
               if(field.type=='empty'){
                   return $scope.emptyComponentBuilder(model+'.'+field.fieldName, field , field.fieldLabel , field.fieldName , 'empty');
               }else if(field.type=='string'){
                   return $scope.inputTextBuilder(model+'.'+field.fieldName , field , field.fieldLabel , field.fieldName , 'text');
               }else if(field.type=='number'){
                   return $scope.inputTextBuilder(model+'.'+field.fieldName , field , field.fieldLabel , field.fieldName , 'number');
               }else if (field.type=='date') {
                   return $scope.inputTextBuilder(model+'.'+field.fieldName , field , field.fieldLabel , field.fieldName , 'date');
               }else if (field.type=='tel') {
                   return $scope.inputTextBuilder(model+'.'+field.fieldName , field , field.fieldLabel , field.fieldName , 'tel');
               }else if (field.type=='email') {
                   return $scope.inputTextBuilder(model+'.'+field.fieldName , field , field.fieldLabel , field.fieldName , 'email');
               }else if (field.type=='url') {
                   return $scope.inputTextBuilder(model+'.'+field.fieldName , field , field.fieldLabel , field.fieldName , 'url');
               }else if (field.type=='time') {
                   return $scope.inputTextBuilder(model+'.'+field.fieldName , field , field.fieldLabel , field.fieldName , 'time');
               }else if (field.type=='datetime') {
                   return $scope.inputTextBuilder(model+'.'+field.fieldName , field , field.fieldLabel , field.fieldName , 'datetime-local');
               }else if (field.type=='password') {
                   return $scope.inputTextBuilder(model+'.'+field.fieldName , field , field.fieldLabel , field.fieldName , 'password');
               }else if (field.type=='boolean') {
                   return $scope.checkboxBuilder(model+'.'+field.fieldName , field , field.fieldLabel , field.fieldName , 'checkbox');
               }else if (field.type=='combobox') {
                   return $scope.comboBoxComponent(model+'.'+field.fieldName , field , field.fieldLabel , field.fieldName , field.value);
               }else if (field.type=='textarea') {
                   return $scope.textAreaBuilder(model+'.'+field.fieldName , field , field.fieldLabel , field.fieldName , 'textarea');
               }else if (field.type=='richeditor') {
                   return $scope.richEditorComponent(model+'.'+field.fieldName , field , field.fieldLabel , field.fieldName , 'richeditor');
               }else if (field.type=='aceeditor') {
                   return $scope.aceEditorComponent(model+'.'+field.fieldName , field , field.fieldLabel , field.fieldName , 'richeditor');
               }else if (field.type=='image') {
                   return $scope.imageComponentBuilder(model+'.'+field.fieldName , field , field.fieldLabel , field.fieldName , 'textarea');
               }else if((field.type=='object') && angular.isDefined(field.metaData)){                         
                  return $scope.manyToOneComponent(model+'.'+field.fieldName , field.fieldLabel , field.fieldName , field.metaData,field,index,modelpath+'.'+field.fieldName);
               }else if((field.type == 'array') && (angular.isDefined(field.metaData))){
                    if(field.target == 'many-to-many'){
                       return $scope.manyToManyComponent(model+'.'+field.fieldName , field.fieldLabel , field.fieldName , field.metaData ,field,index,modelpath+'.'+field.fieldName);
                    }else if(field.target == 'one-to-many'){
                       return $scope.oneToManyComponent(model+'.'+field.fieldName , field.fieldLabel , field.fieldName , field.metaData,field,index,modelpath+'.'+field.fieldName);
                    }else if(field.target == 'many-to-many-list'){
                       return $scope.manyToManyListComponent(model+'.'+field.fieldName , field.fieldLabel , field.fieldName , field.metaData,field,index,modelpath+'.'+field.fieldName);
                    }
               }
        };

       /**
        * 
        * @param {type} model
        * @param {type} fields
        * @param {type} entityName
        * @param {type} viewElem
        * @param {type} metaDataName
        * @param {type} index
        * @param {type} modelpath
        * @returns {@var;viewElem}
        */
        $scope.panelComponent = function(model , fields , entityName , viewElem , metaDataName,index,modelpath){
//            console.log("$scope.panelComponent ========== "+index);
            if(angular.isDefined(viewElem) && (viewElem!=null)){  
              var divElem = viewElem;
              var spanElem_1 = document.createElement('span') ;
              spanElem_1.setAttribute('style' , 'display: inline-block;margin-right: 20px;width: 48%;');
              divElem.appendChild(spanElem_1);
              var spanElem_2 = document.createElement('span');
              spanElem_2.setAttribute('style' , 'display: inline-block;width: 48%;');
              divElem.appendChild(spanElem_2);
              if(fields.length % 2 ==1){
                //fields.push({type:"emailpty" , search:true ,fieldName:" " , fieldLabel:" "})
              }
              if(angular.isDefined(fields)){
                  //Sort of array
                  fields = $filter('orderBy')(fields,'sequence',false);
//                  console.log(" $scope.tabPaneComponent =  "+angular.toJson(fields[0]));         
                  if(fields.length==1){
                      divElem.appendChild($scope.getIhmComponent(model , fields[0] , entityName , metaDataName,index,modelpath));
                      return divElem;
                  }
                  var start = 0 ;
//                  if(fields[0].type=='image'){
//                      divElem.appendChild($scope.getIhmComponent(model , fields[0] , entityName , metaDataName));
//                      start = 1 ;
//                  }
                  for(var i = start ; i<fields.length ; i++){
                      if($scope.panelComponent.type != 'array'|| fields[i].type!='textarea'|| fields[i].type!='richeditor'||fields[i].target=='many-to-many'){
                          if(i%2 == 0){
//                              console.log("$scope.panelComponent ===== "+fields[i].fieldName)
                             spanElem_1.appendChild($scope.getIhmComponent(model , fields[i], entityName , metaDataName,index,modelpath));
                          }else{
                             spanElem_2.appendChild($scope.getIhmComponent(model , fields[i] , entityName , metaDataName,index,modelpath));
                          }
                        }else{
                            
                           divElem.appendChild($scope.getIhmComponent(model , fields[i] , entityName , metaDataName,index,modelpath));
                           
                        }
                  }
              }
             /* var divElem0 = document.createElement('div');
             divElem0.appendChild(divElem);
             console.log(divElem0.innerHTML);*/
              return divElem;
            }
        };


        /**
             Create component tabPane
             model : field to map
            labelText : the Label of the input
            entityName : name of the entity,
            metaData : MetaData which describe de object
        **/
        $scope.tabPaneComponent = function(model , labelText , entityName ,groups ,index,modelpath){
//            console.log("$scope.tabPaneComponent ================= "+index);
            var id = generateurId();
            var divElem = document.createElement('div');
            divElem.setAttribute('role' , 'tabpanel');
            var ulElem = document.createElement('ul');
            ulElem.setAttribute('class' , 'nav  nav-tabs');
            ulElem.setAttribute('role' , 'tablelist');            
            divElem.appendChild(ulElem);
            //Sort of array
            groups = $filter('orderBy')(groups,'sequence',false);
            for(var i=0 ; i < groups.length;i++){
               var liElem = document.createElement('li');
               ulElem.appendChild(liElem);
               liElem.setAttribute('role' , 'presentation');
               var aElem = document.createElement('a');
               liElem.appendChild(aElem);
               aElem.setAttribute('href' , '#'+groups[i].groupName+id);
               aElem.setAttribute('aria-control' , groups[i].groupName+id);
               aElem.setAttribute('role' , 'tab');
               aElem.setAttribute('data-toggle' , 'tab');
               aElem.appendChild(document.createTextNode(groups[i].groupLabel));
            }                        
            
            //Creation du contenu de panel
            var divElem_1 = document.createElement('div');
            divElem.appendChild(divElem_1);
            divElem_1.setAttribute('class' , 'tab-content');
            divElem.appendChild(divElem_1);

            for(var i=0 ; i< groups.length;i++){
                  var divElem_2 = document.createElement('div');
                  divElem_2.setAttribute('role' , 'tabpanel');
                  if(i==0){ 
                        divElem_2.setAttribute('class' , 'tab-pane active');
                    }else{
                       divElem_2.setAttribute('class' , 'tab-pane');
                    }
                  divElem_2.setAttribute('id' , groups[i].groupName+id);
                  //Construction du corps
                 if(angular.isDefined(groups[i].metaArray) && (groups[i].metaArray!=null)){
                      //Cas des données de type oneToManay et ManyToMany
                      var tableElem = null;
//                      console.log(angular.toJson(groups[i].metaArray));  
                      if(groups[i].metaArray.target == 'one-to-many'){
                          tableElem = $scope.oneToManyComponent(model+'.'+groups[i].metaArray.fieldName 
                                            , labelText 
                                            , groups[i].metaArray.metaData.entityName
                                            ,groups[i].metaArray.metaData
                                            ,groups[i].metaArray,index
                                            ,modelpath+'.'+groups[i].metaArray.fieldName);
                      }else if(groups[i].metaArray.target == 'many-to-many-list'){
                          tableElem = $scope.manyToManyListComponent(model+'.'+groups[i].metaArray.fieldName 
                                            , labelText 
                                            , groups[i].metaArray.metaData.entityName
                                            ,groups[i].metaArray.metaData
                                            ,groups[i].metaArray,index
                                            ,modelpath+'.'+groups[i].metaArray.fieldName);
                      }

                      if(angular.isDefined(tableElem)&&tableElem!=null){
                        divElem_2.appendChild(tableElem);
                      }
                      
                  }else if(angular.isDefined(groups[i].columns)&&groups[i].columns.length>0){              
                      $scope.panelComponent(model , groups[i].columns,entityName , divElem_2 ,null,index,modelpath);
                  }

                  divElem_1.appendChild(divElem_2);
            }

             //var divElem0 = document.createElement('div');
             //divElem0.appendChild(divElem);
             //console.log(divElem0.innerHTML);

            return divElem;

        };
        
 
      /**
        Template provider :provide the template for view builder
        @type : template type (list , detail ,kaban , calendar)
      **/
      $scope.viewSelector = function(type){
                                 
                                   var content  = null ;
                                   if(type=='list'){                                    
                                      content ="<div class='panel panel-default container-panel  table-responsive'  id='innerpanel' style='height: 100%;width: 100%;'> <div class='container-heading-panel'> <nav id='listebar' class='navbar navbar-default container-heading-panel'  role='navigation'> <div class='col-sm-12  col-md-12  nav nav-justified navbar-nav container-heading-panel'> <div class='navbar-header col-sm-6 col-md-5  container-heading-panel'> <button type='button'  class='navbar-toggle' data-toggle='collapse'  data-target='#Navbar'> <span class='sr-only'>Toggle Navigation</span> <span class='icon-bar'></span> <span class='icon-bar'></span> <span class='icon-bar'></span> </button> <a  class='navbar-brand' href='#'>{{metaData.listTitle}}</a> </div> <div class='col-sm-6 col-md-7  container-heading-panel'> <form class='navbar-form navbar-search  navbar-right' role='Search' id='filtercontainer' style='width: 100%;' > <div class='input-group' style='width: 100%;'> <span class='input-group-btn pull-left'  style='display: inline-block;width: 20%;'> <button type='button' class='btn btn-search btn-sm btn-default dropdown-toggle' data-toggle='dropdown' id='filtertbtn' style='width: 100%;'> <span class='glyphicon glyphicon-filter'></span> <span class='label-icon'>Filtres</span> <span class='caret'></span> </button> <ul class='dropdown-menu' role='menu'  id='filterActionsId'> <li> <a href='#'> <span class='glyphicon glyphicon-user'></span> <span class='label-icon'>Search By User</span> </a> </li> <li> <a href='#'> <span class='glyphicon glyphicon-book'></span> <span class='label-icon'>Search By Organization</span> </a> </li> </ul> </span> <span class='input-group-btn  pull-left' style='display: inline-block;width: 80%;'> <input type='text' ng-model='searchCriteria' class='form-control input-sm' style='width: 93%;'> <button type='button' class='btn btn-search btn-sm btn-default' ng-click='searchAction()'> <span class='glyphicon glyphicon-search'></span> </button> </span>  </div>  </form> </div> <br /><br /><br /> <div class='btn-toolbar' role='toolbar'  aria-label='Toolbar1'> <div class='btn-group'  role='group'  aria-label='group 1' ng-hide='desablecreate'> <button type='button'  class='btn btn-primary btn-sm' ng-click='addElementAction()'>Creer</button> </div>  <div class='btn-group'  role='group'  aria-label='group 1' ng-hide='desableupdate'> <button type='button'  class='btn btn-default btn-sm'  ng-click='importAction()'  id='importerbtn'>Importer</button> </div>  <div class='btn-group  dropdown'    role='group'  aria-label='group 2' ng-hide='desableprint'> <button type='button'  class='btn btn-default btn-sm dropdown dropdown-toggle' data-toggle='dropdown' aria-haspopup='false'  aria-expanded='true' id='imprimerbtn'> Imprimer <span class='caret'></span> </button> <ul id='print_menus' class='dropdown-menu'  role='menu'  aria-labelledby='imprimerbtn'> <li role='presentation'> <a role='menuitem' tabindex='-1' href='#' ng-click='printAction()'> Imprimer </a> </li> </ul> </div>  <div class='btn-group  dropdown'    role='group'  aria-label='group 2' ng-hide='desableprint'> <button type='button'  class='btn btn-default btn-sm dropdown dropdown-toggle' data-toggle='dropdown' aria-haspopup='false'  aria-expanded='true' id='actionsbtn'  ng-show='showActions()'> Actions <span class='caret'></span> </button> <ul class='dropdown-menu'  role='menu'  aria-labelledby='actionsbtn' id='actions_menu'> <li role='presentation'> <a role='menuitem' tabindex='-1' href='#'  ng-click='exportAction()'>{{exportbtnlabel}}</a> </li> <li role='presentation' ng-hide='desableupdate'> <a role='menuitem' tabindex='-1' href='#'  ng-click='updateAction()'> {{updatebtnlabel}}</a> </li> <li role='presentation'  ng-hide='desableAction'> <a role='menuitem' tabindex='-1' href='#'  ng-click='deleteListAction()'>{{deletebtnlabel}}</a> </li> </ul> </div>  <span class='pull-right'> <div class='btn-group'  role='group'  aria-label='group 3'> <span class='btn btn-default btn-sm'>{{pagination.currentPage}}-{{pagination.endIndex}} / {{pagination.totalPages}}</span> <button type='button'  class='btn btn-default btn-sm' ng-click='pagination.previous()'  ng-disabled='!pagination.hasprevious()'> <span class='glyphicon glyphicon-chevron-left'  aria-hidden='true'></span> </button> <button type='button'  class='btn btn-default btn-sm' ng-click='pagination.next()' ng-disabled='!pagination.hasnext()'> <span class='glyphicon glyphicon-chevron-right'  aria-hidden='true'></span> </button> </div> <div id='viewmodeid'></div></span>  </div> </div> </nav> </div><div class='panel-body container-body-panel'  id='datatable' style='height: 82%;overflow: auto;margin-top: -10px;'> <table class='table table-striped table-hover table-responsive table-sm  table-header'  style='margin-top: -10px;' id='table'> <thead> <tr id='rowheader'> <th><input type='checkbox' ng-model='tableheaderselected' ng-click='onCheckboxClick()'></th> <th>Module Name</th> <th>Module Description</th> </tr> </thead> <tbody id='tablebody'> <tr  ng-repeat=' module in modules' > <td><input type='checkbox' ng-model='module.selected'  ng-click='onRowCheckboxClick(module)'></td> <td>{{module.name}}</td> <td>{{module.shortDescription}}</td> </tr> </tbody> </table>  </div> </div>"     ;
                                   }else if(type=='detail'){
                                     content = "<div class='panel panel-default' id='innerpanel' style='padding:0;height:100%;'> <div class='panel-container' style='height: 100% ;border:0px;'> <nav id='listebar' class='navbar navbar-default detail-heading'  role='navigation'> <div class='navbar-header  col-sm-12  col-md-12'> <button type='button'  class='navbar-toggle' data-toggle='collapse'  data-target='#Navbar'> <span class='sr-only'>Toggle Navigation</span> <span class='icon-bar'></span> <span class='icon-bar'></span> <span class='icon-bar'></span> </button> <a  class='navbar-brand' href='#'>{{metaData.editTitle}} / {{suffixedittitle}}</a> </div> <div class='btn-toolbar' role='toolbar'  aria-label='Toolbar1'> <div class='btn-group'  role='group'  aria-label='group 1'     ng-hide='desablecreateedit'> <button type='button'  class='btn btn-primary btn-sm' ng-click ='saveOrUpdate()'>Enregistrer</button> </div> <div class='btn-group'  role='group'  aria-label='group 1'> <button type='button'  class='btn btn-default btn-sm' ng-click='annulerAction()'>Annuler</button> </div> <div class='btn-group  dropdown'    role='group'  aria-label='group 2'   ng-hide='desableprintedit'> <button type='button'  class='btn btn-default dropdown dropdown-toggle btn-sm' data-toggle='dropdown' aria-haspopup='false'  aria-expanded='true' id='imprimerbtn'> Imprimer <span class='caret'></span> </button> <ul  id='print_menus'  class='dropdown-menu'  role='menu'  aria-labelledby='imprimerbtn'> <li role='presentation'> <a role='menuitem' tabindex='-1' href='#'  ng-click='printAction()'> Imprimer </a> </li> </ul> </div> <div class='btn-group  dropdown'    role='group'  aria-label='group 2'   ng-hide='iscreateOperation()'> <button type='button'  class='btn btn-default dropdown dropdown-toggle btn-sm' data-toggle='dropdown' aria-haspopup='false'  aria-expanded='true' id='imprimerbtn' ng-show='showpjmenu==true'> Pièce(s) jointe(s) <span class='caret'></span> </button> <ul class='dropdown-menu'  role='menu'  aria-labelledby='piecejointebtn' id='pj_menus_id'> <li role='presentation'> <span style='display: inline-block;'> <span style='display: inline-block;margin-right: 15px;'> <a role='menuitem' tabindex='-1' href='#' ng-click='printAction()'> Contrat de travail </a> </span> <span style='display: inline-block;'> <a style='margin-right: 50;'> <span class='glyphicon glyphicon-trash'  aria-hidden='true'></span> </a> </span> </span> </li> <li role='presentation'> <a role='menuitem' tabindex='-1' href='#' ng-click='printAction()'> Ajouter </a> </li> </ul> <input type='file' id='pj_file_input' style='display: none' fileinput='file'  onchange='angular.element(this).scope().gererChangementFichier(event)'> </div> <div class='btn-group  dropdown'    role='group'  aria-label='group 2'   ng-hide='desableupdateedit'> <button type='button'  class='btn btn-default btn-sm dropdown dropdown-toggle' data-toggle='dropdown' aria-haspopup='false'  aria-expanded='true' id='actionsbtn'> Actions <span class='caret'></span> </button> <ul class='dropdown-menu'  role='menu'  aria-labelledby='actionsbtn' id='actions_menu'> <li role='presentation'  ng-hide='showApplication==false'> <a role='menuitem' tabindex='-1' href='#'  ng-click='installAction()'> {{exportbtnlabel}} </a> </li>  <li role='presentation'  ng-hide='desableupdateedit'> <a role='menuitem' tabindex='-1' href='#'  ng-click='updateAction()'> {{updatebtnlabel}} </a> </li> <li role='presentation' ng-hide='desabledeleteedit'> <a role='menuitem' tabindex='-1' href='#'  ng-click='deleteAction()'> {{deletebtnlabel}} </a> </li> </ul> </div> <span class='pull-right'   ng-hide='iscreateOperation()'> <div class='btn-group'  role='group'  aria-label='group 3'> <span class='btn btn-default btn-sm'>{{pagination.currentPage}} / {{pagination.totalPages}}</span> <button type='button'  class='btn btn-default  btn-sm' ng-click='pagination.previousPage()' ng-disabled='!pagination.hasPreviousPage()'> <span class='glyphicon glyphicon-chevron-left'  aria-hidden='true'></span> </button> <button type='button'  class='btn btn-default btn-sm' ng-click='pagination.nextPage()' ng-disabled='!pagination.hasNextPage()'> <span class='glyphicon glyphicon-chevron-right'  aria-hidden='true'></span> </button> </div> </span> <div id='workflow_menu_id'></div> </div> </nav><div id='detail-panel-header'></div> <div class='panel-body panel-container' style='padding:0;border:0px;height:85%; margin-top: -15px;' > <div class='panel panel-default col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1' ><div class='panel-body'  id='detail-panel-body'> <form role='form' class='form-horizontal'  name='myForm' novalidate> <span style='display: inline-block;margin-right: 20px;width: 48%;'> <div class='form-group  col-sm-12  col-md-12'> <img src='img\photo.png'  class='img-responsive' alt='Image '> </div> <div class='form-group  col-sm-12  col-md-12'> <label for='name'>Nom</label> <input type='text' class='form-control'  id='name' placeholder='Votre Nom' ng-required='true' ng-minlength='2'> </div> <div class='form-group  col-sm-12  col-md-12'> <label for='birthday'>Date de Naissance</label> <input type='date' class='form-control'  id='birthday' placeholder='Votre Nom'> </div> <div class='form-group  col-sm-12  col-md-12'> <label for='sexe'>Sexe</label> <div class='input-group'> <select class='form-control'  data-style='btn-default'> <option>Masculin</option> <option>Feminin</option> </select> <span class='input-group-btn'> <button type='button' class='btn btn-default my-group-button' data-toggle='modal' data-target='#myModal'> <span class='glyphicon glyphicon-plus' aria-hidden='true' style='color:blue'></span> </button> </span> </div> </div> </span> <span style='display: inline-block;width: 48%;'> <div class='form-group  col-sm-12  col-md-12'> <label for='description'>Description</label> <input type='text' class='form-control'  id='description' placeholder='Votre Description' ng-minlength='3'> </div> <div class='form-group  col-sm-12  col-md-12'> <label for='heurenaissance'>Heure de Naissance</label> <input type='time' class='form-control'  id='heurenaissance' placeholder='Votre Description'> </div> <div class='form-group  col-sm-12  col-md-12'> <label for='sexe'>Sexe</label> <div class='input-group'> <select class='selectpicker form-control'  multiple data-style='btn-default'> <option>Masculin</option> <option>Feminin</option> </select> <span class='input-group-btn'> <button type='button' class='btn btn-default my-group-button' data-toggle='modal' data-target='#myModal'> <span class='glyphicon glyphicon-plus' aria-hidden='true' style='color:blue'></span> </button> </span> </div> </div> <div class='form-group  col-sm-12  col-md-12'> <label for='actions'>undefined</label> <div class='input-group' > <select class='selectpicker form-control' bootstrap-selectpicker multiple='multiple' data-style='btn-default' ng-model='dataSelects' ng-options='item as item.name for item in currentObject.actions' ng-click='getData(actions)' >  </select> <span class='input-group-btn'> <button class='btn btn-default my-group-button' ng-click='editDialogBuilder(metaData)'> <span class='glyphicon glyphicon-plus' aria-hidden='true' style='color:blue'></span> </button> </span> </div> </div> </span> <div class='table-responsive'> <table class='table  table-striped table-bordered table-hover table-condensed'> <thead> <tr style='font-weight: bold;'> <th>Nom Module</th> <th>Description</th> </tr> </thead> <tbody> <tr> <ul class='nav navbar-nav'> <li> <a href='#'   data-toggle='modal'  data-target='#myModal'  ng-click='editDialogBuilder()'> <span class='glyphicon glyphicon-plus' aria-hidden='true'></span> </a> </li> <li> <a href='#' data-toggle='modal'  data-target='#myModal'> <span class='glyphicon glyphicon-pencil' aria-hidden='true'></span> </a> </li> <li> <a href='#'> <span class='glyphicon glyphicon-trash' aria-hidden='true'></span> </a> </li> <li> <a href='#'  data-toggle='modal'  data-target='#myModal'> <span class='glyphicon glyphicon-eye-open' aria-hidden='true'></span> </a> </li> </ul> </tr> <tr > <td>GC</td> <td>Gestion Commerciale</td> </tr> <tr> <td>MT</td> <td>Gestion de la maintenance</td> </tr> </tbody> </table> </div> <div  role='tabpanel'> <ul class='nav  nav-tabs'  role='tablist'> <li role='presentation'> <a href='#profile'  aria-control='profile'  role='tab'  data-toggle='tab'> Onglet 1 </a> </li> <li role='presentation'> <a href='#message'  aria-control='message'  role='tab'  data-toggle='tab'> Onglet 2 </a> </li> </ul> <div class='tab-content'> <div role='tabpanel'  class='tab-pane active'  id='profile' > Conteneur 1 </div> <div role='tabpanel'  class='tab-pane'  id='message' >  </div> </div> </div> </form> </div> </div> <div class='col-sm-12 col-md-12' style='background-color: white;'   ng-show='shownotepanel()' style='display:none;'> <div class='panel-heading'> <div class='btn-group'  role='group'  aria-label='group 1'> <button type='button'  class='btn btn-primary btn-sm' ng-click ='messagesAction()'>Nouveau Message</button> </div> <div class='btn-group'  role='group'  aria-label='group 1' style='margin-left: 10px;'> <a href='#'  class='a-sm' style='border:none;text-decoration: none;' ng-click ='notesInterneAction()'>Enregistrer une note interne</a> </div> <span class='pull-right'> <div class='btn-group'  role='group'  aria-label='group 1' style='right: 150px;'> <button type='button'  class='btn btn-default btn-sm' ng-click ='suivreAction()' ng-if='activefollower==false'>Ne pas suivre<span class='glyphicon glyphicon-remove' aria-hidden='true'></button> <button type='button'  class='btn btn-default btn-sm' ng-click ='suivreAction()' ng-if='activefollower==true'> Abonnée(s) <span class='glyphicon glyphicon-ok' aria-hidden='true'></span> </button> </div> <div class='btn-group  dropdown'    role='group'  aria-label='group 2'  style='right: 120px;'> <a href='#'  class='dropdown dropdown-toggle btn-sm' data-toggle='dropdown' aria-haspopup='false'  aria-expanded='true' id='imprimerbtn'> <span class='caret'></span> <span class='glyphicon glyphicon-user' aria-hidden='true'></span> </a> <ul class='dropdown-menu'  role='menu'  aria-labelledby='imprimerbtn' id='followermenuid'> <li role='presentation'> <a role='menuitem' tabindex='-1' href='#'  ng-click='editPanelAjoutAborne()'> Ajouter des abonnées </a> </li> <li role='presentation'> <a role='menuitem' tabindex='-1' href='#'  ng-click='editPanelAjoutCanaux()'> Ajouter des canaux </a> </li> <li class='dropdown-divider'></li> </ul> </div> </span>  </div> <div class='panel-heading'> <div class='input-group' ng-show='enablefollowerpanel==true'> <input type='text' class='form-control'  id='name' placeholder='Saisir votre message' ng-required='true' ng-model='dataCache.messageobject.body' ng-minlength='2'> <div class='input-group-btn  dropdown'    role='group'  aria-label='group 2'> <button type='button'  class='btn btn-default dropdown dropdown-toggle btn-sm' data-toggle='dropdown' aria-haspopup='false'  aria-expanded='true' id='pjidbtn'  ng-click='imageClick(\"followerfileinput_id\")'><span class='glyphicon glyphicon-paperclip'  aria-hidden='true' style='color:blue;'></span> <span class='caret'></span> </button> <input type='file' id='followerfileinput_id' style='display: none' fileinput='file'   onchange='angular.element(this).scope().gererChangementFichier2(event)'> </div> <span class='input-group-btn'> <button type='button'  class='btn btn-default btn-sm' ng-click ='sendAction()'> <span class='glyphicon glyphicon-send' aria-hidden='true' style='color:blue'> </span> </button> </span> </div> <div><div  id='followermenu_id'></div> </div></div> <div style='height: 87%;width:100%;overflow: auto;'> <table class='table table-inbox table-hover'  id='tablefollowersid'> <tbody> <tr class='unread'> <td class='inbox-small-cells'> <input type='checkbox' class='mail-checkbox'> </td> <td class='inbox-small-cells'><i class='fa fa-star'></i></td> <td class='view-message  dont-show'>PHPClass</td> <td class='view-message '>Added a new class: Login Class Fast Site</td> <td class='view-message  inbox-small-cells'><i class='fa fa-paperclip'></i></td> <td class='view-message  text-right'>9:27 AM</td> </tr> </tbody> </table> </div>    </div> </div> </div></div>"   ;
                                   }else if(type=="report"){ 
                                       content = "<div class='panel panel-default' id='innerpanel' style='padding:0;height:100%;'> <div class='panel-container' style='height: 100% ;border:0px;'> <nav id='listebar' class='navbar navbar-default detail-heading'  role='navigation'> <div class='navbar-header  col-sm-12  col-md-12'> <button type='button'  class='navbar-toggle' data-toggle='collapse'  data-target='#Navbar'> <span class='sr-only'>Toggle Navigation</span> <span class='icon-bar'></span> <span class='icon-bar'></span> <span class='icon-bar'></span> </button> <a class='navbar-brand' href='#' ng-show='showreporttitle==true'>{{metaData.editTitle}} / {{suffixedittitle}}</a><a class='navbar-brand' href='#' ng-show='showreporttitle==false'>{{currentObject.editTitle}} / {{suffixedittitle}}</a> </div> <div class='btn-toolbar' role='toolbar'  aria-label='Toolbar1'> <div class='btn-group'  role='group'  aria-label='group 1'> <button type='button'  class='btn btn-default btn-sm' ng-click='annulerAction()' ng-hide='hideannuler'>Annuler</button> </div>  </div> </nav>   <div class='panel-body panel-container' style='padding:0;border:0px;height:85%; margin-top: -15px;' > <div id='report'>  </div> </div>";
                                   }else if(type=="calendar"){ 
                                       content = content ="<div class='panel panel-default container-panel  table-responsive'  id='innerpanel' style='height: 100%;width: 100%;'> <div class='container-heading-panel'> <nav id='listebar' class='navbar navbar-default container-heading-panel'  role='navigation'> <div class='col-sm-12  col-md-12  nav nav-justified navbar-nav container-heading-panel'> <div class='navbar-header col-sm-6 col-md-5  container-heading-panel'> <button type='button'  class='navbar-toggle' data-toggle='collapse'  data-target='#Navbar'> <span class='sr-only'>Toggle Navigation</span> <span class='icon-bar'></span> <span class='icon-bar'></span> <span class='icon-bar'></span> </button> <a  class='navbar-brand' href='#'>{{metaData.listTitle}}</a> </div> <div class='col-sm-6 col-md-7  container-heading-panel'> <form class='navbar-form navbar-search  navbar-right' role='Search' id='filtercontainer' style='width: 100%;' > <div class='input-group' style='width: 100%;'> <span class='input-group-btn pull-left'  style='display: inline-block;width: 20%;'> <button type='button' class='btn btn-search btn-sm btn-default dropdown-toggle' data-toggle='dropdown' id='filtertbtn' style='width: 100%;'> <span class='glyphicon glyphicon-filter'></span> <span class='label-icon'>Filtres</span> <span class='caret'></span> </button> <ul class='dropdown-menu' role='menu'  id='filterActionsId'> <li> <a href='#'> <span class='glyphicon glyphicon-user'></span> <span class='label-icon'>Search By User</span> </a> </li> <li> <a href='#'> <span class='glyphicon glyphicon-book'></span> <span class='label-icon'>Search By Organization</span> </a> </li> </ul> </span> <span class='input-group-btn  pull-left' style='display: inline-block;width: 80%;'> <input type='text' ng-model='searchCriteria' class='form-control input-sm' style='width: 93%;'> <button type='button' class='btn btn-search btn-sm btn-default' ng-click='searchAction()'> <span class='glyphicon glyphicon-search'></span> </button> </span>  </div>  </form> </div> <br /><br /><br /> <div class='btn-toolbar' role='toolbar'  aria-label='Toolbar1'> <div class='btn-group'  role='group'  aria-label='group 1' ng-hide='desablecreate'> <button type='button'  class='btn btn-primary btn-sm' ng-click='addElementAction()' ng-hide='true'>Creer</button> </div>  <div class='btn-group'  role='group'  aria-label='group 1' ng-hide='true'> <button type='button'  class='btn btn-default btn-sm'  ng-click='importAction()'  id='importerbtn'>Importer</button> </div>  <div class='btn-group  dropdown'    role='group'  aria-label='group 2' ng-hide='desableprint'> <button type='button'  class='btn btn-default btn-sm dropdown dropdown-toggle' data-toggle='dropdown' aria-haspopup='false'  aria-expanded='true' id='imprimerbtn'> Imprimer <span class='caret'></span> </button> <ul id='print_menus' class='dropdown-menu'  role='menu'  aria-labelledby='imprimerbtn'> <li role='presentation'> <a role='menuitem' tabindex='-1' href='#' ng-click='printAction()'> Imprimer </a> </li> </ul> </div>  <div class='btn-group  dropdown'    role='group'  aria-label='group 2' ng-hide='desableprint'> <button type='button'  class='btn btn-default btn-sm dropdown dropdown-toggle' data-toggle='dropdown' aria-haspopup='false'  aria-expanded='true' id='actionsbtn'  ng-show='showActions()'> Actions <span class='caret'></span> </button> <ul class='dropdown-menu'  role='menu'  aria-labelledby='actionsbtn' id='actions_menu'> <li role='presentation'> <a role='menuitem' tabindex='-1' href='#'  ng-click='exportAction()'>{{exportbtnlabel}}</a> </li> <li role='presentation' ng-hide='desableupdate'> <a role='menuitem' tabindex='-1' href='#'  ng-click='updateAction()'> {{updatebtnlabel}}</a> </li> <li role='presentation'  ng-hide='desableAction'> <a role='menuitem' tabindex='-1' href='#'  ng-click='deleteListAction()'>{{deletebtnlabel}}</a> </li> </ul> </div>  <span class='pull-right'> <div class='btn-group'  role='group'  aria-label='group 3'> <span class='btn btn-default btn-sm'>{{pagination.currentPage}}-{{pagination.endIndex}} / {{pagination.totalPages}}</span> <button type='button'  class='btn btn-default btn-sm' ng-click='pagination.previous()'  ng-disabled='!pagination.hasprevious()'> <span class='glyphicon glyphicon-chevron-left'  aria-hidden='true'></span> </button> <button type='button'  class='btn btn-default btn-sm' ng-click='pagination.next()' ng-disabled='!pagination.hasnext()'> <span class='glyphicon glyphicon-chevron-right'  aria-hidden='true'></span> </button> </div><div id='viewmodeid'></div></span>  </div> </div> </nav> </div>   <div class='panel-body container-body-panel'  id='datatable' style='height: 82%;overflow: auto;margin-top: -10px;'><div class='col-sm-12  col-md-12' id='calendar' ui-calendar='uiConfig.calendar' ng-model='eventSources' calendar='myCalendar' ></div></div> </div>"
                                               
                                   }
                                   return angular.element(content);
                             };
      


 /**
           * 
           * @param {type} metaData
           * @returns {undefined}
           */
          $scope.templateReportList = function(metaData){
              if(metaData){
                  var report =  $scope.tableListComponentReport(metaData);
                  var row = document.createElement("div");
                  row.setAttribute("class","row");
                  var colmd12 = document.createElement("div");
                  colmd12.setAttribute("class","col-md-12");
                  row.appendChild(colmd12);
                  var panel = document.createElement("div");
                  colmd12.appendChild(panel);
                  panel.setAttribute("class" ,"panel panel-default");
                  var header = document.createElement("div");
                  header.setAttribute("class","panel-heading");
                  panel.appendChild(header);
                  h3 = document.createElement("h3");
                  strong = document.createElement("strong");
                  strong.appendChild(document.createTextNode(metaData.listTitle));
                  h3.appendChild(strong);
                  h3.setAttribute("class","panel-title text-center");
                  header.appendChild(h3);
                  //Body Creation
                  body = document.createElement("div");
                  body.setAttribute("class","panel-body")
                  body.appendChild(report);
                  panel.appendChild(body);
                  var divElem = document.createElement('div');
                  divElem.setAttribute('style','width:100%;');
                  divElem.appendChild(row);
                  document.getElementById("report_template").innerHTML = divElem.innerHTML;
              }//end if(metaData)
          };
          
          /**
           * 
           * @param {type} String
           * @returns {undefined}
           */
          $scope.displayReportPanel_HTML = function(script){ 
              var container = document.createElement('div');
              container.innerHTML = script;
//              divElem.setAttribute('style','width:100%;');
              $scope.reportbuilder(container,0);
              document.getElementById("report_template").innerHTML = container.innerHTML;
//              console.log("$scope.displayReportPanel_HTML ============== "+container.innerHTML);
          };
          
          /**
           * 
           * @param {type} html
           * @param {type} conter
           * @returns {undefined}
           */
          $scope.reportbuilder = function(html,counter){
              var k = counter;
              for(var i=0 ; i<html.childNodes.length;i++){
                  k = k+1;
//                  html.childNodes[i].id = k;
                  $scope.computeReportField(html.childNodes[i],html);
                  $scope.reportbuilder(html.childNodes[i],k+1);
              }  
          };
          /**
           * 
           * @param {type} node
           * @param {type} name
           * @returns {undefined}
           */
          $scope.hasAttribute = function(node , name){
              if(node){
                  if(node.attributes){
                      for(var i=0 ;i<node.attributes.length;i++){
                          if(node.attributes[i]&&node.attributes[i].name==name){
                              return true;
                          }
                      }//endfor(var i=0 ;node.attributes.length;i++)
                  }
              }//end if(node)
              return false;
          };
          
          /**
           * 
           * @param {type} node
           * @param {type} name
           * @returns {undefined}
           */
          $scope.getAttribute = function(node , name){
              if(node){
                  if(node.attributes){
                      for(var i=0 ;i<node.attributes.length;i++){
                          if(node.attributes[i]&&node.attributes[i].name==name){
                              return node.attributes[i].value;
                          }
                      }//endfor(var i=0 ;node.attributes.length;i++)
                  }
              }//end if(node)
          };
          /**
           * 
           * @param {type} node
           * @returns {undefined}
           */
          $scope.computeReportField = function(node,html){
              if(node){
                  if($scope.hasAttribute(node,"for")){
//                        console.log("$scope.displayReportPanel_HTML with for ============== "+node.nodeName+" == "+node.nodeValue); 
                        $scope.forNodeBuilder(node,html,null);
                  }else if($scope.hasAttribute(node,"if")){
//                      $scope.ifnodebuilder(node,html);
//                      console.log("$scope.displayReportPanel_HTML with if ============== "+node.nodeName);
                  }else if($scope.hasAttribute(node,"expr")){
//                      var text = $scope.getAttribute(node,"expr");
//                      text = text.replace(/'/g , "\"");
//                      var  result = $scope.exprEval(text,null);
//                      if(result){
//                          node.textContent = result;
//                      }else{
//                          node.textContent = ' ';
//                      }//end if(result)     
//                      console.log("$scope.displayReportPanel_HTML with if ============== "+node.nodeName);
                  }else if($scope.hasAttribute(node,"func")){
//                      var text = $scope.getAttribute(node,"func");
////                      text = text.replace(/'/g , "\"");
//                      var  result = $scope.funcEval(node,null);
//                      if(result){
//                          node.textContent = result;
//                      }else{
//                          node.textContent = ' ';
//                      }//end if(result)     
//                      console.log("$scope.displayReportPanel_HTML with if ============== "+node.nodeName);
                  }else{
                      var text = $scope.getnodevalue(node,null);
                      //console.log("$scope.displayReportPanel_HTML with if ============== "+node.tagName+" === "+node.parentNode.nodeName);
                      if(text){
                          node.nodeValue =text;
                     }//end if(text)
//                     $scope.computeReportField(node,html);
                  }//end if(node.for)
              }//end if(node)
          };
           /**
           * 
           * @param {type} node
           * @param {type} data
           * @returns {undefined}
           */
          $scope.computeDOMNode = function(node , data){
             if(node){
                  if($scope.hasAttribute(node,"for")){      
//                        console.log("$scope.displayReportPanel_HTML with for ============== "+node.nodeName+" == "+node.nodeValue); 
                        $scope.forNodeBuilder(node,null,data);//(node,data,null);
                  }else if($scope.hasAttribute(node,"if")){
                        $scope.ifnodebuilder(node,data);
//                      console.log("$scope.displayReportPanel_HTML with if ============== "+node.nodeName);
                  }else if($scope.hasAttribute(node,"expr")){
                      var text = $scope.getAttribute(node,"expr");
                      text = text.replace(/'/g , "\"");
                      $scope.removeAttribute(node,"expr");
                      var  result = $scope.exprEval(text,data);
                      if(result){
                          node.textContent = result;
                      }else{
                          node.textContent = ' ';
                      }//end if(result)     
//                      console.log("$scope.displayReportPanel_HTML with if ============== "+node.nodeName);
                  }else if($scope.hasAttribute(node,"func")){
                      var text = $scope.getAttribute(node,"func");
//                      text = text.replace(/'/g , "\"");
                      var  result = $scope.funcEval(node,data);
                      if(result){
                          node.textContent = result;
                      }else{
                          node.textContent = ' ';
                      }//end if(result)     
//                      console.log("$scope.displayReportPanel_HTML with if ============== "+node.nodeName);
                  }else{
                       var text = $scope.getnodevalue(node,data);                       
//                       console.log("$scope.computeDOMNode ============== "+text+"======="+node.nodeName+" === "+node.childNodes.length);
                       if(node.childNodes.length==0){
                           node.textContent = text;
                       }else{
                            for(var j=0 ; j<node.childNodes.length;j++){
                                 $scope.computeDOMNode(node.childNodes[j],data);
                            }//end for(var j=0 ; j<node.childNodes.length;i++){                            
                        }
                  }//end if(node.for)
              }//end if(node)
          };
          /**
           * Evaluation d'une expression arithmetique et logique
           * @param {type} exp
           * @param {type} obj
           * @returns {undefined}
           */
         $scope.exprEval = function(exp,obj){
              if(exp){                 
                 var pattern = /(\w+)[.](\w+)/g;
                 var matches = exp.match(pattern);
                 if(matches){
                     for(var i=0 ;i<matches.length;i++){
                          exp=exp.replace(matches[i],$scope.getnodevalue(null,obj,matches[i]));
                     }
                 }
//                 console.log("$scope.exprEval = function(exp,obj) exp = "+exp+" === match ="+angular.toJson(matches));
                 return eval(exp);     
              }//end if(exp)
          };
          /**
           * 
           * @param {type} func
           * @returns {undefined}
           */
          $scope.funcEval = function(node,obj){
              var text = $scope.getAttribute(node,"func");
              text = text.replace(/'/g , "\"");
              var func = angular.fromJson(text);
              if(func && func.type&&func.data&&func.expr){                  
                  if(func.type=='sum'){
                      return $scope.sum(func.expr,$scope.getnodeEntity(node,obj,null));
                  }else if(func.type=='product'){
                      return $scope.product(func.expr,$scope.getnodeEntity(node,obj,null));
                  }else if(func.type=='avarage'){
                      return $scope.avarage(func.expr,$scope.getnodeEntity(node,obj,null));
                  }
              }//end if(func && func.type&&func.data&&func.expr)
              return "undefined";
          };
          /**
           * Sommes les elements enevaluant exp
           * @param {type} datas
           * @param {type} exp
           * @returns {undefined}
           */
          $scope.sum = function(exp,obj){
              if(exp){
                  if(angular.isArray(obj)){
                      var result = 0 ;
                     for(var i=0 ; i<obj.length;i++){
                         result += $scope.exprEval(obj[i],exp);
                     }//end for(var i=0 ; i<datas.length;i++)
                     return result;
                  }else if(angular.isObject(obj)){
                      return $scope.exprEval(obj,exp);
                  }else{
                      return $scope.exprEval(exp);
                  }//end if(datas)
              }//end if(exp)
              return "undefined";
          };
          /**
           * Sommes les elements enevaluant exp
           * @param {type} datas
           * @param {type} exp
           * @returns {undefined}
           */
          $scope.product = function(exp,obj){
              if(exp){
                  if(angular.isArray(obj)){
                      var result = 0 ;
                     for(var i=0 ; i<obj.length;i++){
                         result *= $scope.exprEval(obj[i],exp);
                     }//end for(var i=0 ; i<datas.length;i++)
                     return result;
                  }else if(angular.isObject(obj)){
                      return $scope.exprEval(obj,exp);
                  }else{
                      return $scope.exprEval(exp);
                  }//end if(datas)
              }//end if(exp)
              return "undefined";
          };
          /**
           * Sommes les elements enevaluant exp
           * @param {type} datas
           * @param {type} exp
           * @returns {undefined}
           */
          $scope.avarage = function(exp,obj){
              if(exp){
                  if(angular.isArray(obj)&&obj.length>0){
                      var result = 0 ;
                     for(var i=0 ; i<obj.length;i++){
                         result += $scope.exprEval(obj[i],exp);
                     }//end for(var i=0 ; i<datas.length;i++)
                     return (result/obj.length);
                  }else if(angular.isObject(obj)){
                      return $scope.exprEval(obj,exp);
                  }else{
                      return $scope.exprEval(exp);
                  }//end if(datas)
              }//end if(exp)
              return "undefined";
          };
         
           /**
           * 
           * @param {type} node
           * @param {type} datas
           * @param {type} html
           * @returns {undefined}
           */
          $scope.ifnodebuilder = function(node,data){
                try{
                  if(node){
                      var text = $scope.getAttribute(node,"if");
                      text = text.replace(/'/g , "\"");
                      var ifobject = angular.fromJson(text);
                      $scope.removeAttribute(node , "if");
                      if(ifobject && ifobject['op1']&&ifobject['op2']&&ifobject['cond']){
                          if(angular.isString(ifobject['op1'])){//La source données est l'object courant
                              var op1value = ifobject['op1'];
                              var value_1 = $scope.getnodevalue(null,data,op1value);
                              var value_2 = $scope.getnodevalue(null,data,ifobject['op2']);
                              var result = $scope.conditionexpr(value_1,value_2,ifobject['cond']);
//                              console.log(node.nodeName+"$scope.ifnodebuilder = function(node,html,data) ========= "+value_1+" == "+value_2+" == "+result);
                              if(result){
                                 $scope.computeDOMNode(node,data);                                 
                              }else{
                                  var parentnode = node.parentNode;
                                  parentnode.removeChild(node); 
                              }//end if(result)
                          }
                      }//end if(ifobject && ifobject['op1']&&ifobject['op2']&&ifobject['cond'])
                  }//end if(node && datas)
                  
               }catch(ex){
                    console.error(ex);
                    commonsTools.showMessageDialog(ex);
                }
          };
          /**
           * 
           * @param {type} value_1
           * @param {type} value_2
           * @param {type} cond
           * @returns {undefined}
           */
          $scope.conditionexpr = function(value_1,value_2,cond){
              if(value_1 && value_2 && cond){
                 if(cond=='<'){
                     return value_1<value_2;
                 }else if(cond=='>'){
                     return value_1>value_2;
                 }else if(cond=='=='){
                     return value_1==value_2;
                 }else if(cond=='==='){
                     return value_1===value_2;
                 }else if(cond=='>='){
                     return value_1>=value_2;
                 }else if(cond=='<='){
                     return value_1>value_2;
                 }                     
                 
              }//end if(value_1 && value_2 && cond)
              return false;
          };
         /**
          * 
          * @param {type} node
          * @param {type} html
          * @param {type} data
          * @returns {undefined}
          */
          $scope.forNodeBuilder = function(node,html,data){
            try{  
              if(node){
                  var text = $scope.getAttribute(node,"for");
                  text = text.replace(/'/g , "\"");
                  //console.log(text);
                  //var text ='{"source":"datas","var":"obj"}';
                  var forobject = angular.fromJson(text);
//                  var id = $scope.getAttribute(node,"id");
//                  console.log("$scope.forNodeBuilder = == "+forobject['source']);                
                  if(forobject && forobject['source']){
                      var datas = null ;
                      if(angular.isString(forobject['source'])){
                          if(forobject['source']=="selected"){
                              datas = $scope.selectedObjects;
                          }else if(forobject['source']=="datas"){
                              datas = $scope.datas;
                          }else if(forobject['source']=="requete"){
                              datas = $scope.temporalDatas;
                          }else {
                              var part = forobject['source'].split(".");
                              if(part.length>1){
                                  var key = part[1];
                                  datas = data[key];
//                                  console.log("$scope.forNodeBuilder ====== "+angular.toJson(data));
                              }
                          }
                          $scope.fortargetbuilder(node,datas,html);
                      }else if(forobject.source.model&&forobject.source.entity&&forobject.source.method){
                          commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%");
                          var url ="http://"+$location.host()+":"+$location.port()+"/"+forobject.source.model+"/"+forobject.source.entity+"/"+forobject.source.method;
                          $http.get(url)
                                  .then(function(response){
                                      var datas = response.data;
                                      $scope.fortargetbuilder(node,datas,html);
                                      commonsTools.hideDialogLoading();
                                  },function(error){
                                      commonsTools.hideDialogLoading();
                                      commonsTools.showMessageDialog(error);
                                  });
                      }
                  }//end if(forobject)
              }   
            }catch(ex){
                console.error(ex);
                commonsTools.showMessageDialog(ex);
            }
          };
          /**
           * 
           * @param {type} cible
           * @param {type} source
           * @returns {undefined}
           */
          $scope.copyAttribute = function(node , source){
              if(node && source){
                  if(node.attributes){
                      for(var i=0 ;i<node.attributes.length;i++){
                          if(node.attributes[i]&&node.attributes[i].name!="for"&&node.attributes[i].name!="if"){
                              source.setAttribute(node.attributes[i].name,node.attributes[i].value);
                          }
                      }//endfor(var i=0 ;node.attributes.length;i++)
                  }
              }//end if(node)
          };
          /**
           * 
           * @param {type} node
           * @param {type} name
           * @returns {undefined}
           */
         $scope.removeAttribute = function(node , name){
             if(node){
                  if(node.attributes){                      
                    node.attributes.removeNamedItem(name);                      
                  }
              }//end if(node)
         };
          /**
           * 
           * @param {type} node
           * @param {type} datas
           * @returns {undefined}
           */
          $scope.fortargetbuilder = function(node, datas,html){
              if(node && datas){                 
                  $scope.removeAttribute(node,"for");
                  for(var i=0 ; i<datas.length;i++){
                       var nd = node.cloneNode(true);
                       for(var j=0 ; j<nd.childNodes.length;j++){
                              $scope.computeDOMNode(nd.childNodes[j],datas[i]);
                      }//end for(var j=0 ; j<node.childNodes.length;i++){
                      node.parentNode.appendChild(nd);              
//                      container.appendChild(htmlElem);                
                  }//end for(var i=0 ; i<node.childNodes.length;i++)
                  node.parentNode.removeChild(node);
              }//end if(node && datas)
          };
          /**
           * 
           * @param {type} node
           * @param {type} obj
           * @returns {undefined}
           */
          $scope.getnodevalue = function(node,obj,text){
              var value = null;
              if(!angular.isDefined(node)|| node==null){
                  value = text;
              }else {
                  value = node.textContent;
              }
              if(value && angular.isString(value)){
                    var part = value.split(".");
                    if(part.length>1){
                          var data = obj;
                          if(part[0]=='data'){
                              data=$scope.currentObject;
                          }else if(part[0]=='user'){
                              data= $rootScope.globals.user;
                          }else if(part[0]=='company'){
                              data= $rootScope.globals.company;
                          }
//                          console.log("$scope.getnodevalue ======= "+value+" === "+angular.toJson(data)+" ==== "+part[1]);
                          //console.log("$scope.displayReportPanel_HTML with if ============== "+angular.toJson(part));
                              //Suite du traitement
                          if(data){
                              var result = data;
                              for(var i=1 ; i<part.length;i++){
                                  result = result[part[i]];
                                  if(!result){
                                      return "";                              
                                  }
                              }
                              if(angular.isObject(result)){
                                  return result.designation;
                              }
                              return result;
                          }//end if(data)
                      }else{
                          if(angular.isObject(value)){
                              return value['designation'];
                          }//end if(angular.isObject(value))
                          return value;
                      }//end if(part.length>1){
                }        
                return value;
                
          };
          /**
           * 
           * @param {type} node
           * @param {type} obj
           * @returns {undefined}
           */
          $scope.getnodeEntity = function(node,obj,text){
              var value = node.textContent;
              if(!angular.isDefined(node)|| node==null){
                  value = text;
              }
                if(value){
                    var part = value.split(".");
                    if(part.length>1){
                          var data = obj;
                          if(part[0]=='data'){
                              data=$scope.currentObject;
                          }else if(part[0]=='user'){
                              data= $rootScope.globals.user;
                          }else if(part[0]=='company'){
                              data= $rootScope.globals.company;
                          }
                          //console.log("$scope.displayReportPanel_HTML with if ============== "+angular.toJson(part));
                              //Suite du traitement
                          return data;
                      }else{                          
                          return value;
                      }//end if(part.length>1){
                }                
                return null;
                
          };
          /**
           * 
           * @returns {undefined}
           */
          $scope.displayDashboardPanel = function(){
                $scope.showreporttitle = false;
                $scope.suffixedittitle = $scope.currentObject.designation;
                var  contentElem = $scope.viewSelector('report') ;
                var compileFn = $compile(contentElem);
                compileFn($scope);
                var container = commonsTools.dashboardContainerBuilder($scope.currentObject);
                var items = contentElem.find('div');
                for(var i=0; i<items.length;i++){
                   if(items.eq(i).attr("id")=="report"){
                         items.eq(i).replaceWith(container);                               
                   }  
                }//enn$d for(var i=0; i<items.length;i++){
                 // ///Remplacement dans la vue
                var items = $element.find("div");
                for(var i=0; i<items.length;i++){
                     if(items.eq(i).attr("id")=="innerpanel"){
                           items.eq(i).replaceWith(contentElem);
                            //console.log(" ======================= on a trouve report  innerpanel");
                     }//end if(items.eq(i).attr("id")=="innerpanel")  
                }//end if(items.eq(i).attr("id")=="innerpanel")
                return contentElem;
          };
          /**
           * 
           * @returns {undefined}
           */
          $scope.displayReportPanel = function(script){
                //alert("::::::::::::::::::::  "+$scope.selectedObjects+" :::::: "+$scope.windowType);
                var  contentElem = $scope.viewSelector('report') ;
                var compileFn = $compile(contentElem);
                compileFn($scope);
                if($scope.windowType=='list'){
                   if($scope.selectedObjects.length>0){
                       $scope.templateReportList($scope.metaData);
                   }else{
                        $scope.notifyWindow("Veuillez selectionner au moins une ligne" ,"<br/>","warning");
                    }//end if($scope.selectedObjects.length>0)
                }else if($scope.windowType=='report'){
                    $scope.displayReportPanel_HTML(script);
                }else{
                    $scope.editReportPanelComponent($scope.metaData);
                }
                var doc = new jsPDF("l", "pt", "a4");
                var specialElementHandlers = {
                    '#editor': function (element, renderer) {
                        return true;
                    }
                };
                var source = $("#report_template")[0],
                        margins={
                            top: 40,
                            bottom: 60,
                            left: 60,
                            width: 700
                        };
                        doc.fromHTML(
                             source,
                             margins.left,
                             margins.top,
                             {
                                  // y coord
                                width: margins.width, // max width of content on PDF
                                'elementHandlers':specialElementHandlers
                             },
                             function(dispose){
                                  var iframe = document.createElement('iframe');
                                    iframe.setAttribute('style','position:absolute;right:0; top:15%; bottom:0; height:100%; width:100%;');
                                    //document.body.appendChild(iframe);
                                    iframe.src = doc.output('datauristring')
                                    iframe.setAttribute('name' ,"internal");                  
                                    var div = document.createElement('div');
                                    div.appendChild(iframe);
                                    div.setAttribute("target" ,"internal");
                                    var items = contentElem.find('div');
                                    for(var i=0; i<items.length;i++){
                                       if(items.eq(i).attr("id")=="report"){
                                             items.eq(i).replaceWith(iframe);                               
                                       }  
                                   }//enn$d for(var i=0; i<items.length;i++){
                                   //var compileFn = $compile(contentElem);

                                   // ///Remplacement dans la vue
                                  var items = $element.find("div");
                                  for(var i=0; i<items.length;i++){
                                       if(items.eq(i).attr("id")=="innerpanel"){
                                             items.eq(i).replaceWith(contentElem);
                                              //console.log(" ======================= on a trouve report  innerpanel");
                                       }  
                                  }
                                  document.getElementById("report_template").innerHTML = "";
                                 //doc.save("Test.pdf");
                             },
                             margins                                 
                      );
              
          };
          
          /**
             * 
             * @param {type} message
             * @returns {undefined}
             */
            $scope.followerpiecejointeMenu = function(message){                 
                  var divElem = document.createElement('div');
                  divElem.setAttribute('id','followermenu_id');             
                  var pbjmenu = document.querySelector('#followermenu_id');
                    if(pbjmenu!=null){
                         pbjmenu.replaceWith(divElem);
                    }                
//                 $scope.showDialogLoading("Chargement ...","white","#9370db","0%","0%"); 
                   var pieces = message.piecesjointe;
                    if(pieces && pieces.length>0){
                        divElem.setAttribute('class','media');                  
                         var divElem1 = document.createElement('div');
                        divElem.appendChild(divElem1);
                        divElem1.setAttribute("class","media-body");
                        for(var i=0 ;i<pieces.length;i++){
                            var act = pieces[i];                            
                            var spanElem = document.createElement("span");
                            divElem1.appendChild(spanElem);
                            spanElem.setAttribute("style","display: inline-block;");
                            var span_1 = document.createElement("span");
                            span_1.setAttribute("style","inline-block;margin-right: 15px;");
                            spanElem.appendChild(span_1);
                            var span_2 = document.createElement("span");
                            span_2.setAttribute("style","inline-block;");
//                            span_2.setAttribute("ng-hide","desabledelete==true");
                            spanElem.appendChild(span_2);
                            var aElem = document.createElement('a');
                            span_1.appendChild(aElem);
                            aElem.setAttribute('role','menuitem');
                            aElem.setAttribute('tabindex','-1');
                            aElem.setAttribute('href','#');
                            aElem.setAttribute('ng-click',"followerpiecejointeviewAction("+act.id+",'"+act.filename+"')");
                            aElem.setAttribute('style',"text-decoration: none;");
                            aElem.appendChild(document.createTextNode(act.attachename)) ;
                            aElem = document.createElement('a');
                            span_2.appendChild(aElem);
                            aElem.setAttribute('style','margin-right: 50;text-decoration: none;');
                            aElem.setAttribute('tabindex','-1');
                            aElem.setAttribute('href','#');
                            aElem.setAttribute('ng-click',"followerpiecejointedeleteAction('"+act.filename+"')");
                            var span_3 = document.createElement('span');
                            aElem.appendChild(span_3);
                            span_3.setAttribute('class','glyphicon glyphicon-trash');
                            span_3.setAttribute('aria-hidden','true');
                        }//end for(var i=0 ;i<pieces.length;i++) 
                        var compileFn = $compile(divElem);
                        compileFn($scope);
                      }//end if(pieces && pieces.length>0) desableupdateedit                                      
                    
                        //commonsTools.hideDialogLoading();
//                            var divElem0 = document.createElement("div");
//                            divElem0.appendChild(divElem);
//                            console.log(divElem0.innerHTML); 
//               return viewElem;
        };
          /**
           * 
           * @param {type} message
           * @returns {undefined}
           */
          $scope.followerpiecejointeMenuOld = function(message){                 
                  var ulElem = document.createElement('ul');
                  ulElem.setAttribute('class','dropdown-menu');
                  ulElem.setAttribute('role','menu');
                  ulElem.setAttribute('aria-labelledby','piecejointebtn');
                  ulElem.setAttribute("id","followermenu_id");  
                  var pbjmenu = document.querySelector('#followermenu_id');
                    if(pbjmenu!=null){
                         pbjmenu.replaceWith(ulElem);
                    }                
//                 $scope.showDialogLoading("Chargement ...","white","#9370db","0%","0%"); 
                   var pieces = message.piecesjointe;
                    if(pieces && pieces.length>0){
                        for(var i=0 ;i<pieces.length;i++){
                            var act = pieces[i];
                            var liElem = document.createElement('li');
                            liElem.setAttribute('role','presentation');
                            ulElem.appendChild(liElem);
                            var spanElem = document.createElement("span");
                            spanElem.setAttribute("style","display: inline-block;");
                            liElem.appendChild(spanElem);
                            var span_1 = document.createElement("span");
                            span_1.setAttribute("style","inline-block;margin-right: 15px;");
                            spanElem.appendChild(span_1);
                            var span_2 = document.createElement("span");
                            span_2.setAttribute("style","inline-block;");
//                            span_2.setAttribute("ng-hide","desabledelete==true");
                            spanElem.appendChild(span_2);
                            var aElem = document.createElement('a');
                            span_1.appendChild(aElem);
                            aElem.setAttribute('role','menuitem');
                            aElem.setAttribute('tabindex','-1');
                            aElem.setAttribute('href','#');
                            aElem.setAttribute('ng-click',"followerpiecejointeviewAction("+act.id+",'"+act.filename+"')");
                            aElem.setAttribute('style',"text-decoration: none;");
                            aElem.appendChild(document.createTextNode(act.attachename)) ;
                            aElem = document.createElement('a');
                            span_2.appendChild(aElem);
                            aElem.setAttribute('style','margin-right: 50;text-decoration: none;');
                            aElem.setAttribute('tabindex','-1');
                            aElem.setAttribute('href','#');
                            aElem.setAttribute('ng-click',"followerpiecejointedeleteAction('"+act.filename+"')");
                            var span_3 = document.createElement('span');
                            aElem.appendChild(span_3);
                            span_3.setAttribute('class','glyphicon glyphicon-trash');
                            span_3.setAttribute('aria-hidden','true');
                        }//end for(var i=0 ;i<pieces.length;i++)    
                      }//end if(pieces && pieces.length>0) desableupdateedit
                    var liElem = document.createElement('li');
                    liElem.setAttribute('role','presentation');
                    ulElem.appendChild(liElem);
                    var aElem = document.createElement('a');
                    aElem.setAttribute('role','menuitem');
                    aElem.setAttribute('tabindex','-1');
                    aElem.setAttribute('href','#');
                    aElem.setAttribute('ng-click','imageClick("pjfileinput_id")');
                    aElem.appendChild(document.createTextNode('Ajouter')) ;                            
                    liElem.appendChild(aElem);      
                    ulElem.appendChild(liElem);                       
                    var compileFn = $compile(ulElem);
                    compileFn($scope);
                        //commonsTools.hideDialogLoading();
//                            var divElem = document.createElement("div");
//                            divElem.appendChild(ulElem);
//                            alert(divElem.innerHTML); 
//               return viewElem;
        };
          //
          
           /**
         * Construction du menu des pieces jointes
         * @param {type} metaData
         * @returns {undefined}
         */
        $scope.piecejointeMenu = function(viewElem,model,metaData){
            if(metaData && (metaData.activefilelink==true)){
                 if($scope.currentAction && angular.isString($scope.currentAction)){
                    $scope.currentAction = angular.fromJson($scope.currentAction);
                 }//end if($scope.currentAction && angular.isString($scope.currentAction))
                  var ulElem = document.createElement('ul');
                  ulElem.setAttribute('class','dropdown-menu');
                  ulElem.setAttribute('role','menu');
                  ulElem.setAttribute('aria-labelledby','piecejointebtn');
                  ulElem.setAttribute("id","pj_menus_id");  
                  
                  if(viewElem!=null){
                    var items = viewElem.find("ul");
                    for(var i=0; i<items.length;i++){
                      if(items.eq(i).attr("id")=="pj_menus_id"){
                            items.eq(i).replaceWith(ulElem);
                      }  
                     }
                 }else{
                     var pbjmenu = document.querySelector('#pj_menus_id');
                     if(pbjmenu!=null){
                          pbjmenu.replaceWith(ulElem);
                     }
                 }//end if(viewElem!=null)
//                 $scope.showDialogLoading("Chargement ...","white","#9370db","0%","0%"); 
                var url = "http://"+$location.host()+":"+$location.port()+"/kerencore/piecejointe/pj/"+model.serial+"/"+model.id;
                $http.get(url)
                        .then(function(response){
                            var pieces = response.data;
                            if(pieces && pieces.length>0){
                                for(var i=0 ;i<pieces.length;i++){
                                    var act = pieces[i];
                                    var liElem = document.createElement('li');
                                    liElem.setAttribute('role','presentation');
                                    ulElem.appendChild(liElem);
                                    var spanElem = document.createElement("span");
                                    spanElem.setAttribute("style","display: inline-block;");
                                    liElem.appendChild(spanElem);
                                    var span_1 = document.createElement("span");
                                    span_1.setAttribute("style","inline-block;margin-right: 15px;");
                                    spanElem.appendChild(span_1);
                                    var span_2 = document.createElement("span");
                                    span_2.setAttribute("style","inline-block;");
                                    span_2.setAttribute("ng-hide","desabledelete==true");
                                    spanElem.appendChild(span_2);
                                    var aElem = document.createElement('a');
                                    span_1.appendChild(aElem);
                                    aElem.setAttribute('role','menuitem');
                                    aElem.setAttribute('tabindex','-1');
                                    aElem.setAttribute('href','#');
                                    aElem.setAttribute('ng-click',"piecejointeviewAction("+act.id+",'"+act.filename+"')");
                                    aElem.setAttribute('style',"text-decoration: none;");
                                    aElem.appendChild(document.createTextNode(act.attachename)) ;
                                    aElem = document.createElement('a');
                                    span_2.appendChild(aElem);
                                    aElem.setAttribute('style','margin-right: 50;text-decoration: none;');
                                    aElem.setAttribute('tabindex','-1');
                                    aElem.setAttribute('href','#');
                                    aElem.setAttribute('ng-click',"piecejointedeleteAction("+act.id+",'"+act.filename+"')");
                                    var span_3 = document.createElement('span');
                                    aElem.appendChild(span_3);
                                    span_3.setAttribute('class','glyphicon glyphicon-trash');
                                    span_3.setAttribute('aria-hidden','true');
                                }//end for(var i=0 ;i<pieces.length;i++)    
                            }//end if(pieces && pieces.length>0) desableupdateedit
                           if($scope.desableupdateedit==false||$scope.desableupdate==false||$rootScope.globals.user.intitule=='Administrateur'){ 
                                var liElem = document.createElement('li');
                                liElem.setAttribute('role','presentation');
                                ulElem.appendChild(liElem);
                                var aElem = document.createElement('a');
                                aElem.setAttribute('role','menuitem');
                                aElem.setAttribute('tabindex','-1');
                                aElem.setAttribute('href','#');
                                aElem.setAttribute('ng-click','imageClick("pj_file_input")');
                                aElem.appendChild(document.createTextNode('Ajouter')) ;                            
                                liElem.appendChild(aElem);      
                                ulElem.appendChild(liElem);
                           }//end if($scope.desableupdate==tre)
                            var compileFn = $compile(ulElem);
                            compileFn($scope);
                            commonsTools.hideDialogLoading();
//                            var divElem = document.createElement("div");
//                            divElem.appendChild(ulElem);
//                            alert(divElem.innerHTML);
                        },function(error){
                            commonsTools.hideDialogLoading();
                            commonsTools.showMessageDialog(error);
                        });
                         
            }//end if(metaData && (metaData.activefilelien==true) 
//               return viewElem;
        };
          /**
           * 
           * @param {type} viewElem
           * @returns {undefined}
           */
          $scope.buildPrintActionsMenu = function(viewElem){
              if($scope.currentAction && angular.isString($scope.currentAction)){
                  $scope.currentAction = angular.fromJson($scope.currentAction);
               }//end if($scope.currentAction && angular.isString($scope.currentAction))
                var ulElem = document.createElement('ul');
                ulElem.setAttribute('class','dropdown-menu');
                ulElem.setAttribute('role','menu');
                ulElem.setAttribute('aria-labelledby','imprimerbtn');
                ulElem.setAttribute("id","print_menus");
                var liElem = document.createElement('li');
                liElem.setAttribute('role','presentation');
                ulElem.appendChild(liElem);
                var aElem = document.createElement('a');
                aElem.setAttribute('role','menuitem');
                aElem.setAttribute('tabindex','-1');
                aElem.setAttribute('href','#');
                aElem.setAttribute('ng-click','printAction()');
                aElem.appendChild(document.createTextNode('Imprimer')) ;
                liElem.appendChild(aElem);
                if($scope.currentAction.records&&$scope.currentAction.records.length>0){
                  for(var i=0 ; i<$scope.currentAction.records.length;i++){
                        var act = $scope.currentAction.records[i];
                        var liElem = document.createElement('li');
                        liElem.setAttribute('role','presentation');
                        liElem.setAttribute('ng-hide','desableupdateedit');
                        ulElem.appendChild(liElem);
                        var aElem = document.createElement('a');
                        aElem.setAttribute('role','menuitem');
                        aElem.setAttribute('tabindex','-1');
                        aElem.setAttribute('href','#');
                        aElem.setAttribute('ng-click',"customPrintAction('"+act.id+"')");
                        aElem.appendChild(document.createTextNode(act.titre)) ;
                        liElem.appendChild(aElem);
                   }//end for(var i=0 ; i<$scope.currentAction.actions.length;i++){
                    var items = viewElem.find("ul");
                    for(var i=0; i<items.length;i++){

                         if(items.eq(i).attr("id")=="print_menus"){
                               items.eq(i).replaceWith(ulElem);
                         }  
                    }
                   
               }//end if($scope.currentAction.records&&$scope.currentAction.records.length>0)
//               var divElem = document.createElement("div");
//               divElem.appendChild(ulElem);
//               console.log(divElem.innerHTML);
               return viewElem;
          };
          /**
           * 
           * @param {type} viewElem
           * @returns {unresolved}
           */
          $scope.buildActionsMenu = function(viewElem , type,index){
               if($scope.currentAction && angular.isString($scope.currentAction)){
                  $scope.currentAction = angular.fromJson($scope.currentAction);
               }
               var ulElem = document.createElement('ul');
                ulElem.setAttribute('class','dropdown-menu');
                ulElem.setAttribute('role','menu');ulElem.setAttribute('aria-labelledby','aria-labelledby');
                ulElem.setAttribute("id","actions_menu");
                if($scope.windowType=='list'){
                    var liElem = document.createElement('li');
                    liElem.setAttribute('role','presentation');
                    ulElem.appendChild(liElem);
                   var aElem = document.createElement('a');
                   aElem.setAttribute('role','menuitem');aElem.setAttribute('tabindex','-1');
                   aElem.setAttribute('href','#');aElem.setAttribute('ng-click','exportAction()');
                   aElem.appendChild(document.createTextNode('{{exportbtnlabel}}')) ;
                   liElem.appendChild(aElem);
                   //Bloc 2
                    liElem = document.createElement('li');
                    liElem.setAttribute('role','presentation');
                    liElem.setAttribute('ng-hide','desableupdate');
                    ulElem.appendChild(liElem);
                    aElem = document.createElement('a');
                    aElem.setAttribute('role','menuitem');aElem.setAttribute('tabindex','-1');
                    aElem.setAttribute('href','#');aElem.setAttribute('ng-click','updateAction()');
                    aElem.appendChild(document.createTextNode('{{updatebtnlabel}}')) ;
                    liElem.appendChild(aElem);
                     //Bloc 3
                    liElem = document.createElement('li');
                    liElem.setAttribute('role','presentation');
                    liElem.setAttribute('ng-hide','desabledelete');
                    ulElem.appendChild(liElem);
                    aElem = document.createElement('a');
                    aElem.setAttribute('role','menuitem');aElem.setAttribute('tabindex','-1');
                    aElem.setAttribute('href','#');aElem.setAttribute('ng-click','deleteListAction()');
                    aElem.appendChild(document.createTextNode('{{deletebtnlabel}}')) ;
                    liElem.appendChild(aElem);
                }else{
                    var liElem = document.createElement('li');
                    liElem.setAttribute('role','presentation');
                    liElem.setAttribute('ng-hide','showApplication==false');
                    ulElem.appendChild(liElem);
                   var aElem = document.createElement('a');
                   aElem.setAttribute('role','menuitem');aElem.setAttribute('tabindex','-1');
                   aElem.setAttribute('href','#');aElem.setAttribute('ng-click','installAction()');
                   aElem.appendChild(document.createTextNode('{{exportbtnlabel}}')) ;
                   liElem.appendChild(aElem);
                   //Bloc 2
                    liElem = document.createElement('li');
                    liElem.setAttribute('role','presentation');
                    liElem.setAttribute('ng-hide','desableupdateedit');
                    ulElem.appendChild(liElem);
                    aElem = document.createElement('a');
                    aElem.setAttribute('role','menuitem');aElem.setAttribute('tabindex','-1');
                    aElem.setAttribute('href','#');aElem.setAttribute('ng-click','updateAction()');
                    aElem.appendChild(document.createTextNode('{{updatebtnlabel}}')) ;
                    liElem.appendChild(aElem);
                     //Bloc 3
                    liElem = document.createElement('li');
                    liElem.setAttribute('role','presentation');
                    liElem.setAttribute('ng-hide','desabledeleteedit');
                    ulElem.appendChild(liElem);
                    aElem = document.createElement('a');
                    aElem.setAttribute('role','menuitem');aElem.setAttribute('tabindex','-1');
                    aElem.setAttribute('href','#');aElem.setAttribute('ng-click','deleteAction()');
                    aElem.appendChild(document.createTextNode('{{deletebtnlabel}}')) ;
                    liElem.appendChild(aElem);
                }//Ajout des actions de  
               if($scope.currentAction.actions && $scope.currentAction.actions.length>0){                  
                   for(var i=0 ; i<$scope.currentAction.actions.length;i++){
                        var act = $scope.currentAction.actions[i];
                        var liElem = document.createElement('li');
                        liElem.setAttribute('role','presentation');
                        liElem.setAttribute('ng-hide','desableupdateedit');
                        ulElem.appendChild(liElem);
                        var aElem = document.createElement('a');
                        aElem.setAttribute('role','menuitem');aElem.setAttribute('tabindex','-1');
                        aElem.setAttribute('href','#');
                        aElem.setAttribute('ng-click',"buttonAction("+act.value+" , '"+act.type+"',null,'"+index+"')");
                        aElem.appendChild(document.createTextNode(act.label)) ;
                        liElem.appendChild(aElem);
                        //console.log("$scope.buildPrintActionsMenu ================ "+angular.toJson(act));
                
                   }//end for(var i=0 ; i<$scope.currentAction.actions.length;i++){
                   //console.log("========================  "+viewElem.innerHTML)
                   if(type=='javascript'){
                        var item = viewElem.getElementById('actions_menu');
                        if(item){
                            item.parentNode.replaceChild(item,ulElem);
                        }
                   }else if(type=='angular'){
                        var items = viewElem.find("ul");
                        for(var i=0; i<items.length;i++){

                             if(items.eq(i).attr("id")=="actions_menu"){
                                   items.eq(i).replaceWith(ulElem);
                             }  
                        }
                   }
                   
               }
               return viewElem;
          };
          /**
             Affichage du panel d'edition
          **/
          $scope.displayEditPanel = function(){
              if($scope.currentAction && angular.isString($scope.currentAction)){
                  $scope.currentAction = angular.fromJson($scope.currentAction);
              }
              $scope.enablefollowerpanel = false;
              $scope.desablecreateedit = $scope.isviewOperation()||!$scope.canCreate()||!$scope.canUpdate();
              $scope.desableupdateedit = $scope.isupdateOperation()||!$scope.canUpdate()||$scope.iscreateOperation();
              $scope.desabledeleteedit = !$scope.canDelete()||$scope.iscreateOperation()||($scope.showApplication==true&&$scope.currentObject.active==true);;
              $scope.desableprintedit=$scope.iscreateOperation()||!$scope.canPrint();
              $scope.innerWindowType = false;
              var listElem  = null ; 
              var content = $scope.viewSelector('detail') ;
              listElem = angular.element(content);
             //Construction Header
             listElem = $scope.buildActionsMenu(listElem,'angular',0);  
             listElem = $scope.buildPrintActionsMenu(listElem);             
             var viewElem = $scope.editPanelComponent('currentObject' , $scope.metaData,null,0,'currentObject');   
             var headerElem = $scope.editPanelHeader('currentObject' , $scope.metaData,0);       
             $scope.showpjmenu = false ;
             if($scope.metaData.activefilelink==true &&($scope.windowType=="update"||$scope.windowType=="view")){
                 $scope.showpjmenu = true ;
                 $scope.piecejointeMenu(listElem,$scope.currentObject,$scope.metaData);
             }//end if($scope.metaData.activefilelien==true &&($scope.windowType=="update"||$scope.windowType=="view"))
             if($scope.metaData.activatefollower==true && $scope.windowType!='new'){
                 var url = "http://"+$location.host()+":"+$location.port()+"/kerencore/follower/entity/"+$scope.currentObject.serial+"/"+$scope.currentObject.id;
                 $http.get(url)
                         .then(function(response){
                             $scope.dataCache['follower'] = response.data;                             
                             if(!$scope.dataCache['follower']){
                                    var url = "http://"+$location.host()+":"+$location.port()+"/kerencore/follower/meta";
                                    $http.get(url)
                                            .then(function(response){
                                                $scope.dataCache['follower'] = $scope.createFreeEmptyObject(response.data);
                                                //Ajout des champs de baseElement
                                                $scope.dataCache['follower'].id=-1;
                                                $scope.dataCache['follower'].compareid=-1;
                                                $scope.dataCache['follower'].designation="";
                                                $scope.dataCache['follower'].editTitle="";
                                                $scope.dataCache['follower'].listTitle="";
                                                $scope.dataCache['follower'].moduleName="kerencore";
                                                $scope.dataCache['follower'].selected=false;
                                                $scope.dataCache['follower'].createonfield=true;
                                                $scope.dataCache['follower'].desablecreate=false;
                                                $scope.dataCache['follower'].desabledelete=false;
                                                $scope.dataCache['follower'].serial=null;
                                                $scope.dataCache['follower'].activefilelien=false;
                                                $scope.dataCache['follower'].footerScript=null;
                                                $scope.dataCache['follower'].activatefollower=false;
                                                $scope.dataCache['follower'].entityid=$scope.currentObject.id;
                                                $scope.dataCache['follower'].entityserial= $scope.currentObject.serial; 
                                                $scope.dataCache['follower'].messages = new Array();
                                                $scope.dataCache['follower'].canaux = new Array();
                                                $scope.dataCache['follower'].abonnes = new Array();
                                                $scope.buildfollowermenu();
                                                $scope.buildFollowerMessagesView(listElem,$scope.dataCache['follower'].messages);
                                            },function(error){
                                                commonsTools.showMessageDialog(error);
                                            });     
                                }else{//end if(!$scope.dataCache['follower']){
                                    $scope.buildfollowermenu();
                                    $scope.buildFollowerMessagesView(listElem,$scope.dataCache['follower'].messages);
                                }//end if(!$scope.dataCache['follower'])
                             
                         },function(error){
                             commonsTools.showMessageDialog(error);
                         });
                             
             }
              //             console.log("$scope.displayEditPanel = function() === "+$scope.showpjmenu+" ==== "+$scope.metaData.activefilelink);
             
             //Creation de l'entete du formultaire
             if(headerElem){
                    var items = listElem.find("div");
                    for(var i=0; i<items.length;i++){

                         if(items.eq(i).attr("id")=="workflow_menu_id"){
                               items.eq(i).replaceWith(headerElem);
                         }  
                    }
             }//end if(headerElem){
            var items = listElem.find("div");
            for(var i=0; i<items.length;i++){
                 
                 if(items.eq(i).attr("id")=="detail-panel-body"){
                       items.eq(i).replaceWith(viewElem);
                 }  
            }
            var compileFn = $compile(listElem);
            compileFn($scope);

            ///Remplacement dans la vue
            var items = $element.find("div");
            for(var i=0; i<items.length;i++){
                 
                 if(items.eq(i).attr("id")=="innerpanel"){
                       items.eq(i).replaceWith(listElem);
                 }  
            }
             $timeout(function() {                
                $('.selectpicker').selectpicker('refresh');
                
              });
          };

          /**    
             Affichage du panel List
          **/
          $scope.displayListPanel = function(){
//              console.log("$scope.displayListPanel =========== "+$scope.windowType+" ====== "+$scope.previousType);
               $scope.enablefollowerpanel = false;               
               if($scope.previousType && $scope.previousType=="calendar"){
                   $scope.windowType = 'calendar';               
                   var viewElem =  $scope.calendarPanelComponent($scope.metaData); 
                   viewElem = $scope.buildPrintActionsMenu(viewElem);
               }else{
                    $scope.windowType = 'list';               
                    var viewElem =  $scope.listPanelComponent($scope.metaData);  
                    //Construction Header
                    viewElem = $scope.buildActionsMenu(viewElem,'angular',0);  
                    viewElem = $scope.buildPrintActionsMenu(viewElem);
               }//end if($scope.previousType && $scope.previousType=="calendar")
               var compileFn = $compile(viewElem);
               compileFn($scope);
               var items = $element.find("div");
               for(var i=0; i<items.length;i++){
                   
                   if(items.eq(i).attr("id")=="innerpanel"){
                         items.eq(i).replaceWith(viewElem);
                   }  
               }
               $scope.loadData();
              
          };

         /**
          * 
          * @param {type} model
          * @returns {$scope.dataCache|$scope.currentObject|@var;data}
          */
          $scope.getCurrentModel = function(model){
//                   console.log("$scope.getCurrentModel ========================= "+model);
                    var part = model.split(".");              
                    var data = $scope.currentObject;
                    if(part[0]=='dataCache'){
                        data = $scope.dataCache;
                    }//end if(part[0]=='dataCache')
                    if(part.length==1){
                        return data;
                    }else{
                          var model =data;
                          for(var i=1 ; i<part.length;i++){
                            /*if((prop==part[1]) || (prop=="'"+part[1]+"'"))*/{
                                //console.log(prop+"=====editDialogBuilder  ***************Youpi************ "+angular.isArray($scope.currentObject[prop]));
                              if(model[part[i]]){
                                  model = model[part[i]];
                              } 
                            }//end for(var i=1 ; i<part.length;i++)
                           return model;
                      }                 
                   
               }

          };     
          

        /**
         * 
         * @param {type} model
         * @returns {principal_L379.principalAnonym$25.controller.$scope.metaData}
         */
          $scope.getCurrentMetaData = function(model){
                if(angular.isString(model)){                

                      var part = model.split(".");              
                     var metaData = $scope.metaData;
                     if(part[0]=='temporalData'){
                        metaData = $scope.temporalMetaData;
                     }//end if(part[0]=='temporalData')
//                     console.log("$scope.getCurrentMetaData = ========================= "+model+" ============== "+angular.toJson(metaData));
                
                     if(part.length==1){
                         return metaData;
                     }else{
                         for(var k=1;k<part.length;k++){
                                for(var i=0 ; i<metaData.columns.length ;i++){
                                    if(part[k]==metaData.columns[i].fieldName){
     //                                    console.log("!!!!!!!"+part[1]+"=====editDialogBuilder  ===== "+metaData.columns[i].fieldName+" ******* "+angular.toJson(metaData.columns[i].metaData));                               
                                         metaData = metaData.columns[i].metaData;
                                    }//end if(part[1]==$scope.metaData.columns[i].fieldName){

                                }//end for(var i=0 ; i<$scope.metaData.columns.length ;i++){

                               //Recherches dans les groups
                               if(metaData.groups){
                                  for(var i=0 ; i<metaData.groups.length;i++){
                                      if(metaData.groups[i]){
//                                          console.log("!!!!!!!====editDialogBuilder  ===== "+metaData.groups[i].groupName+" ******* "+metaData.groups[i].columns.length);
                                           var groupe = metaData.groups[i];
                                           if(metaData.groups[i].columns){
                                                var taille = metaData.groups[i].columns.length;
                                                var columns = metaData.groups[i].columns;
                                                for(var j=0 ; j < taille ;j++){
                                                     if(columns && (part[k]== columns[j].fieldName)){
                                                           metaData = columns[j].metaData;
                                                      }
                                                }//end for(var j=0 ; j < taille ;j++){
                                            }//end if(metaData.groups[i].columns)
                                          //Cas des metaArray
                                            if(groupe.metaArray&&groupe.metaArray.fieldName==part[k]){
                                                metaData = groupe.metaArray.metaData;
                                            }
                                      }
                                      
                                  }
                               }//end if(metaData.groups){
                           }//end for(var i=1;i<part.length;i++){
                           return metaData;
                     }
                }else{
                    return metaData;
                }           
          };


        /**
         * 
         * @param {type} model
         * @param {type} item
         * @param {type} type
         * @param {type} entityName
         * @param {type} moduleName
         * @param {type} index
         * @returns {undefined}
         */
        $scope.editDialogBuilder = function(model , item , type ,entityName , moduleName,index,modelpath){           
          //Traitement du currentObject     
             //Affeectation du model dans l'object temporaire  
//             console.log("$scope.editDialogBuilder ================ "+model+" == "+item+" == "+item+" === "+type+" === "+entityName+" ==== "+moduleName+" === "+index+" === "+modelpath);
             var key = commonsTools.keygenerator(modelpath);           
             if(angular.isString(model)){
               $scope.temporalModel = $scope.getCurrentModel(modelpath);       
               $scope.dataCache[key+"model"] = $scope.temporalModel;
             }else{
                 $scope.temporalModel = model ;
                 $scope.dataCache[key+"model"] = model;
             }             
             var edittitle = 'Nouveau';
             $scope.innerWindowType = 'new';
             if(item){
                  edittitle = item.designation;
                  $scope.innerWindowType = 'update';
             }
             var metaData = $scope.getCurrentMetaData(modelpath) ;
           if(!metaData){
              var exprFn = $parse($scope.currentMetaDataPath);     
              metaData = exprFn($scope); 
           }//end if(!metaData){
           $scope.temporalMetaData = metaData;
            $scope.temporalData = {};  
            $scope.dataCache[""+key] = $scope.createFreeEmptyObject(metaData);
//            console.log("$scope.editDialogBuilder =============== "+index+" == "+model+" === "+key+" === "+angular.toJson($scope.dataCache[""+key+"model"])+" === ");
            if(item){
                angular.extend($scope.temporalData,item);
                 angular.extend($scope.dataCache[""+key],item);
                $scope.temporalDatas.push(item);                
             }           
           //angular.extend($scope.temporalData,$scope.selectedObjects[0]);              
           //console.log("editDialogBuilder ==== "+metaData.entityName);
           var viewElem =  document.createElement('div'); //;
           var bodyID = '';
           var endIndex = index;
           if(endIndex==1){
               bodyID = 'modalbody';
               viewElem.setAttribute('id' , 'modalbody');
           }else if(endIndex==2){
               viewElem.setAttribute('id' , 'gmodalbody');
               bodyID = 'gmodalbody';
           }else if(endIndex==3){
               viewElem.setAttribute('id' , 'modalbody1');
               bodyID = 'modalbody1';
           }else if(endIndex==4){
               viewElem.setAttribute('id' , 'modalbod2');
               bodyID = 'modalbody2';
           } //end if(index==1)
              
//           viewElem.setAttribute("style","heigth:70%;");
          var headerElem = $scope.editPanelHeader("dataCache."+key , metaData,index);  
          var editPanel =  $scope.editPanelComponent("dataCache."+key ,metaData,'new',index,modelpath);
//            console.log("editDialogBuilder  =====  ************* Vous avez cliquez :::: "+model+" ***** "+editPanel+" ==== "+type+" ::: "+entityName+" ::: "+moduleName+" == "+angular.toJson(metaData));
           if(headerElem){
               viewElem.appendChild(headerElem);
           }//end if(headerElem)
           viewElem.appendChild(editPanel);
           //Construction du footer
           var footerDiv = document.createElement('div');
           footerDiv.setAttribute('class' , 'modal-footer');
//           footerDiv.setAttribute('id' , 'modalfooter');
           var footerID = '';
           if(index==1){
               footerID = 'modalfooter';
              footerDiv.setAttribute('id' , 'modalfooter');
           }else if(index==2){
               footerDiv.setAttribute('id' , 'gmodalfooter');
               footerID = 'gmodalfooter';
           }else if(index==3){
               footerDiv.setAttribute('id' , 'modalfooter1');
               footerID = 'modalfooter1';
           }else if(index==4){
               footerDiv.setAttribute('id' , 'modalfooter2');
               footerID = 'modalfooter2';
           } //end if(index==1)
           var buttonElem = document.createElement('button');
           footerDiv.appendChild(buttonElem);
           buttonElem.setAttribute('class' , 'btn btn-primary');
           buttonElem.setAttribute('ng-click' , "addDialogAction('"+model+"' , '"+type+"','"+entityName+"' , '"+moduleName+"',null,"+(index+1)+",'"+modelpath+"')");
           buttonElem.appendChild(document.createTextNode('Save Change'));
           //Button annuler
           buttonElem = document.createElement('button');
           footerDiv.appendChild(buttonElem);
           buttonElem.setAttribute('class' , 'btn btn-default');
           buttonElem.setAttribute('data-dismiss' , "modal");
           buttonElem.setAttribute('type' , 'button');
           buttonElem.setAttribute('ng-click' , "annulerAction2('"+model+"')");
           buttonElem.appendChild(document.createTextNode('Annuler'));
           //Entete modal
           var titleheader = document.createElement('h4');
           var titleID = '';
//           titleheader.setAttribute('class','modal-title');           
           if(index==1){
               titleID='modaltitle';
              titleheader.setAttribute('id','modaltitle');
           }else if(index==2){
               titleheader.setAttribute('id','gmodaltitle');
               titleID = 'gmodaltitle';
           }else if(index==3){
               titleheader.setAttribute('id','modaltitle1');
               titleID = 'modaltitle1';
           }else if(index==4){
               titleheader.setAttribute('id','modaltitle2');
               titleID = 'modaltitle2';
           } //end if(index==1)
           titleheader.appendChild(document.createTextNode(metaData.editTitle+" / "+edittitle));
            //console.log(viewElem.innerHTML);
           var compileFn = $compile(viewElem);
           compileFn($scope);
           // $('.selectpicker').selectpicker('refresh');
           var compileFoot = $compile(footerDiv);
           compileFoot($scope);
           var items = $(document).find("div");
           for(var i=0; i<items.length;i++){               
               if(items.eq(i).attr("id")==bodyID){
                     items.eq(i).replaceWith(viewElem);
                      //console.log("$scope.editDialogBuilder = function(model , item , type) ::: "+$scope.currentMetaDataPath);
          
               } else if(items.eq(i).attr("id")==footerID){
                   items.eq(i).replaceWith(footerDiv);
               } 
           } 

           
           items = $(document).find("h4");
           for(var i=0; i<items.length;i++){               
               if(items.eq(i).attr("id")==titleID){
                     items.eq(i).replaceWith(titleheader);
                      //console.log("$scope.editDialogBuilder = function(model , item , type) ::: "+$scope.currentMetaDataPath);          
               } 
           } 
           
            $timeout(function() {                
                $('.selectpicker').selectpicker('refresh');
                
              });
        };
        
        
        /**
          * 
          * @param {type} metaData
          * @returns {Element}
          */
          $scope.listPanelDialogComponent = function(metaData){
             //console.log("$scope.listPanelDialogComponent = "+fieldName)
//             $scope.innerWindowType = true;
              $scope.dataCache.selectedObjects = new Array();
             var tableElem = document.createElement('table');
             tableElem.setAttribute('class' , 'table table-sm table-striped table-hover table-responsive ');
             //tableElem.setAttribute('style' , 'margin-top: -10px;');
             //tableElem.setAttribute('id' , 'table');
             //Creation du table header
             var theadElem = document.createElement('thead');
             tableElem.appendChild(theadElem);
             //Creation entete
             var  rheadElem = document.createElement('tr');
             rheadElem.setAttribute('class' ,'table-header');
             theadElem.appendChild(rheadElem); 
             var thElem0 = document.createElement('th');
             var inputElem0 = document.createElement('input');
             inputElem0.setAttribute('type' , 'checkbox');
             inputElem0.setAttribute('ng-model' , 'tableheaderselected');
             //inputElem0.setAttribute('ng-click' , 'onCheckboxClick()');
            thElem0.appendChild(inputElem0);
            rheadElem.appendChild(thElem0);
            //Traitement des champs columns
            if(metaData.columns){
                for(var i=0 ; i< metaData.columns.length;i++){
                  if(angular.isDefined(metaData.columns[i].search)
                            &&(metaData.columns[i].search==true)){
                      if(metaData.columns[i].type!='array'&&metaData.columns[i].type!='image'&&metaData.columns[i].type!='textarea'&&metaData.columns[i].type!='richeditor'){  
                        var thElem = document.createElement('th');
                        thElem.innerHTML = metaData.columns[i].fieldLabel;
                        rheadElem.appendChild(thElem);
                       }
                     }
                }
            }
            //Traitement des groups
            if(metaData.groups){
                //Cas des columns
                for(var i=0 ; i<metaData.groups.length;i++){
                    if(metaData.groups[i]&&metaData.groups[i].columns){
                        for(var j=0 ; j< metaData.groups[i].columns.length;j++){
                            if(angular.isDefined(metaData.groups[i].columns[j].search)
                                      &&(metaData.groups[i].columns[j].search==true)){
                                 if(metaData.groups[i].columns[j].type!='array'&&metaData.groups[i].columns[j].type!='image'
                                         &&metaData.groups[i].columns[j].type.type!='textarea'&&metaData.groups[i].columns[j].type.type!='richeditor'){   
                                    var thElem = document.createElement('th');
                                    thElem.innerHTML = metaData.groups[i].columns[j].fieldLabel;
                                    rheadElem.appendChild(thElem);
                                }
                            }
                        }//end For
                    }//Fin traitement des colommes
                }
            }

            //Creation du corps du tableau
            var tbodyElem = document.createElement('tbody');
            tableElem.appendChild(tbodyElem);
            var rbodyElem = document.createElement('tr');
            rbodyElem.setAttribute('ng-repeat' , "obj in dataCache."+metaData.entityName);
            tbodyElem.appendChild(rbodyElem);
            var tdElem = document.createElement('td');
            rbodyElem.appendChild(tdElem);
            rbodyElem.setAttribute('style' , "cursor: pointer;");
            inputElem0 = document.createElement('input');
            inputElem0.setAttribute('type' , 'checkbox');
            inputElem0.setAttribute('ng-model' , 'obj.selected');
            inputElem0.setAttribute('ng-click' , "onRowCheckboxClickDialog(obj)");
            tdElem.appendChild(inputElem0);
            if(metaData.columns){ 
                for(var i=0 ; i< metaData.columns.length;i++){
                     if(angular.isDefined(metaData.columns[i].search)
                           &&(metaData.columns[i].search==true)){
                         if(metaData.columns[i].type!='array'&&metaData.columns[i].type!='image'&&metaData.columns[i].type!='textarea'&&metaData.columns[i].type!='richeditor'){  
                           var thElem = document.createElement('td');
                           //thElem.setAttribute('ng-click' , "viewAction(obj)");
                           if(metaData.columns[i].type=='object'){
                             thElem.innerHTML = "{{obj."+metaData.columns[i].fieldName+".designation}}";
                           }else{
                             thElem.innerHTML = "{{obj."+metaData.columns[i].fieldName+"}}";
                           }
                           rbodyElem.appendChild(thElem);
                        }
                     }
                }
            }
             //Traitement des groups
            if(metaData.groups){
                //Cas des columns
                for(var i=0 ; i<metaData.groups.length;i++){
                    if(metaData.groups[i]&&metaData.groups[i].columns){
                        for(var j=0 ; j< metaData.groups[i].columns.length;j++){
                            if(angular.isDefined(metaData.groups[i].columns[j].search)
                                      &&(metaData.groups[i].columns[j].search==true)){
                                  if(metaData.groups[i].columns[j].type!='array'&&metaData.groups[i].columns[j].type!='image'
                                          &&metaData.groups[i].columns[j].type!='textarea'&&metaData.groups[i].columns[j].type!='richeditor'){   
                                    var thElem = document.createElement('td');
                                    //thElem.setAttribute('ng-click' , "viewAction(obj)");
                                    if(metaData.groups[i].columns[j].type=='object'){
                                      thElem.innerHTML = "{{obj."+metaData.groups[i].columns[j].fieldName+".designation}}";
                                    }else{
                                      thElem.innerHTML = "{{obj."+metaData.groups[i].columns[j].fieldName+"}}";
                                    }
                                    rbodyElem.appendChild(thElem);
                                }
                            }
                        }//end For
                    }//Fin traitement des colommes
                }
            }           
            return tableElem;
         };

         $scope.listPanelDialogComponentExter = function(metaData){
             //console.log("$scope.listPanelDialogComponent = "+fieldName)
//             $scope.innerWindowType = true;
              $scope.dataCache.selectedObjects = new Array();
             var tableElem = document.createElement('table');
             tableElem.setAttribute('class' , 'table table-sm table-striped table-hover table-responsive ');
             //tableElem.setAttribute('style' , 'margin-top: -10px;');
             //tableElem.setAttribute('id' , 'table');
             //Creation du table header
             var theadElem = document.createElement('thead');
             tableElem.appendChild(theadElem);
             //Creation entete
             var  rheadElem = document.createElement('tr');
             rheadElem.setAttribute('class' ,'table-header');
             theadElem.appendChild(rheadElem);             
            //Traitement des champs columns
            if(metaData.columns){
                for(var i=0 ; i< metaData.columns.length;i++){
                  if(angular.isDefined(metaData.columns[i].search)
                            &&(metaData.columns[i].search==true)){
                      if(metaData.columns[i].type!='array'&&metaData.columns[i].type!='image'&&metaData.columns[i].type!='textarea'&&metaData.columns[i].type!='richeditor'){  
                        var thElem = document.createElement('th');
                        thElem.innerHTML = metaData.columns[i].fieldLabel;
                        rheadElem.appendChild(thElem);
                       }
                     }
                }
            }
            //Traitement des groups
            if(metaData.groups){
                //Cas des columns
                for(var i=0 ; i<metaData.groups.length;i++){
                    if(metaData.groups[i]&&metaData.groups[i].columns){
                        for(var j=0 ; j< metaData.groups[i].columns.length;j++){
                            if(angular.isDefined(metaData.groups[i].columns[j].search)
                                      &&(metaData.groups[i].columns[j].search==true)){
                                 if(metaData.groups[i].columns[j].type!='array'&&metaData.groups[i].columns[j].type!='image'
                                         &&metaData.groups[i].columns[j].type.type!='textarea'&&metaData.groups[i].columns[j].type.type!='richeditor'){   
                                    var thElem = document.createElement('th');
                                    thElem.innerHTML = metaData.groups[i].columns[j].fieldLabel;
                                    rheadElem.appendChild(thElem);
                                }
                            }
                        }//end For
                    }//Fin traitement des colommes
                }
            }

            //Creation du corps du tableau
            var tbodyElem = document.createElement('tbody');
            tableElem.appendChild(tbodyElem);
            var rbodyElem = document.createElement('tr');
            rbodyElem.setAttribute('ng-repeat' , "obj in temporalDatas");
            tbodyElem.appendChild(rbodyElem);
            var tdElem = document.createElement('td');
//            rbodyElem.appendChild(tdElem);
            rbodyElem.setAttribute('style' , "cursor: pointer;");            
            if(metaData.columns){ 
                for(var i=0 ; i< metaData.columns.length;i++){
                     if(angular.isDefined(metaData.columns[i].search)
                           &&(metaData.columns[i].search==true)){
                         if(metaData.columns[i].type!='array'&&metaData.columns[i].type!='image'&&metaData.columns[i].type!='textarea'&&metaData.columns[i].type!='richeditor'){  
                           var thElem = document.createElement('td');
                           //thElem.setAttribute('ng-click' , "viewAction(obj)");
                           if(metaData.columns[i].type=='object'){
                             thElem.innerHTML = "{{obj."+metaData.columns[i].fieldName+".designation}}";
                           }else{
                             thElem.innerHTML = "{{obj."+metaData.columns[i].fieldName+"}}";
                           }
                           rbodyElem.appendChild(thElem);
                        }
                     }
                }
            }
             //Traitement des groups
            if(metaData.groups){
                //Cas des columns
                for(var i=0 ; i<metaData.groups.length;i++){
                    if(metaData.groups[i]&&metaData.groups[i].columns){
                        for(var j=0 ; j< metaData.groups[i].columns.length;j++){
                            if(angular.isDefined(metaData.groups[i].columns[j].search)
                                      &&(metaData.groups[i].columns[j].search==true)){
                                  if(metaData.groups[i].columns[j].type!='array'&&metaData.groups[i].columns[j].type!='image'
                                          &&metaData.groups[i].columns[j].type!='textarea'&&metaData.groups[i].columns[j].type!='richeditor'){   
                                    var thElem = document.createElement('td');
                                    //thElem.setAttribute('ng-click' , "viewAction(obj)");
                                    if(metaData.groups[i].columns[j].type=='object'){
                                      thElem.innerHTML = "{{obj."+metaData.groups[i].columns[j].fieldName+".designation}}";
                                    }else{
                                      thElem.innerHTML = "{{obj."+metaData.groups[i].columns[j].fieldName+"}}";
                                    }
                                    rbodyElem.appendChild(thElem);
                                }
                            }
                        }//end For
                    }//Fin traitement des colommes
                }
            }           
            return tableElem;
         };
        /**
         * 
         * @param {type} model
         * @param {type} index
         * @returns {undefined}
         */
        $scope.listDialogBuilder = function(model,index,modelpath){
//            $scope.innerWindowType = true;
            //var parts = model.split(".");
            var metaData = $scope.getCurrentMetaData(model);     
            if(!metaData){
                return ;
            }
           //Chargement des donn�es
           console.log("$scope.listDialogBuilder ===== "+index);     
           $scope.temporalPagination.beginIndex=0;
           $scope.temporalPagination.currentPage=1;
           $scope.temporalPagination.module = metaData.moduleName;
           $scope.temporalPagination.model = metaData.entityName;
           var url="http://"+$location.host()+":"+$location.port()+"/"+angular.lowercase(metaData.moduleName)+"/"+angular.lowercase(metaData.entityName)+"/count";
           $http.get(url)
                   .then(function(response){
//                           console.log("$scope.listDialogBuilder = function(model) :::::: "+angular.toJson(response.data)+" === "+angular.isNumber(angular.fromJson(angular.toJson(response.data))));
                           var itemscount = response.data.value;
                           $scope.temporalPagination.totalPages = response.data.value;
                           $scope.temporalPagination.endIndex = $scope.temporalPagination.pageSize;
                           if(itemscount<$scope.temporalPagination.pageSize){
                               $scope.temporalPagination.endIndex = itemscount;
                           }
                           $scope.getData2("dataCache."+metaData.entityName,metaData.entityName,metaData.moduleName);
                        //Traitement du currentObject       
                           //Affeectation du model dans l'object temporaire              
                         var viewElem =  document.createElement('div'); //;  
                         var bodyID = "unkown";
                         var endIndex = index;
                         if(endIndex==1){
                             bodyID = "modalbody";
                         }else if(endIndex==2){
                             bodyID = "gmodalbody";
                         }else if(endIndex==3){
                             bodyID = "modalbody1";
                         }else if(endIndex==4){
                             bodyID = "modalbody2";
                         }
                         viewElem.setAttribute('id' , bodyID);
                         var searchElem = document.createElement("div");
                         searchElem.innerHTML = "<nav id='listebar' class='navbar navbar-default container-heading-panel'  role='navigation'> <div class='col-sm-12  col-md-12  nav nav-justified navbar-nav container-heading-panel'> <div class='navbar-header col-sm-12 col-md-12  container-heading-panel'> </div> <div class='col-sm-12 col-md-12  container-heading-panel'> <form class='navbar-form navbar-search  navbar-right' role='Search' id='listfiltercontainer' style='width: 100%;' > <div class='input-group' style='width: 100%;'> <span class='input-group-btn  pull-left' style='display: inline-block;width: 100%;'> <input type='text' ng-model='searchCriteria' class='form-control input-sm' style='width: 97%;'> <button type='button' class='btn btn-search btn-sm btn-default'  ng-click='listsearchAction("+angular.toJson(model)+")'> <span class='glyphicon glyphicon-search'></span> </button> </span>  </div>  </form> </div>  <span class='pull-right'> <div class='btn-group'  role='group'  aria-label='group 3'> <span class='btn btn-default btn-sm'>{{temporalPagination.currentPage}}-{{temporalPagination.endIndex}} / {{temporalPagination.totalPages}} </span> <button type='button'  class='btn btn-default btn-sm' ng-click='temporalPagination.previous_2()'  ng-disabled='!temporalPagination.hasprevious()'> <span class='glyphicon glyphicon-chevron-left'  aria-hidden='true'></span> </button> <button type='button'  class='btn btn-default btn-sm' ng-click='temporalPagination.next_2()' ng-disabled='!temporalPagination.hasnext()'> <span class='glyphicon glyphicon-chevron-right'  aria-hidden='true'></span> </button> </div> </span> </div> </div> </nav>";
                         viewElem.appendChild(searchElem);
                         //Construction de la partie recherche
                         var editPanel =  $scope.listPanelDialogComponent(metaData,index);
                         var bodyElem = document.createElement('div');
                         bodyElem.setAttribute('style','overflow: auto;margin-top: -20px;');
                         bodyElem.appendChild(editPanel);
                         viewElem.appendChild(bodyElem);           
                         //Construction du footer
                         var footerDiv = document.createElement('div');
                         footerDiv.setAttribute('class' , 'modal-footer');
                         var footerID ="";
                         if(endIndex==1){
                             footerID = "modalfooter";
                         }else if(endIndex==2){
                             footerID = "gmodalfooter";
                         }else if(endIndex==3){
                             footerID = "modalfooter1";
                         }else if(endIndex==4){
                             footerID = "modalfooter2";
                         }
                         footerDiv.setAttribute('id' , footerID);
                         var buttonElem = document.createElement('button');
                         footerDiv.appendChild(buttonElem);
                         buttonElem.setAttribute('class' , 'btn btn-primary');
                         buttonElem.setAttribute('ng-click' , "addDialogAction('"+model+"' , 'list','"+metaData.entityName+"' , '"+metaData.moduleName+"',null,"+(index+1)+",'"+modelpath+"')");
                         buttonElem.appendChild(document.createTextNode('Save Change'));
                         //Button annuler
                         buttonElem = document.createElement('button');
                         footerDiv.appendChild(buttonElem);
                         buttonElem.setAttribute('class' , 'btn btn-default');
                         buttonElem.setAttribute('data-dismiss' , "modal");
                         buttonElem.setAttribute('type' , 'button');
                         buttonElem.appendChild(document.createTextNode('Annuler'));
                         //Entete modal
                         var titleheader = document.createElement('h4');
                         titleheader.setAttribute('class','modal-title');
                         var titleID ="";
                         if(endIndex==1){
                             titleID = "modaltitle";
                         }else if(endIndex==2){
                             titleID = "gmodaltitle";
                         }else if(endIndex==3){
                             titleID = "modaltitle1";
                         }else if(endIndex==4){
                             titleID = "modaltitle2";
                         }
                         titleheader.setAttribute('id',titleID);
                         titleheader.appendChild(document.createTextNode(metaData.listTitle));
                          //console.log(viewElem.innerHTML);
                         var compileFn = $compile(viewElem);
                         compileFn($scope);
                         var compileFoot = $compile(footerDiv);
                         compileFoot($scope);
                         var items = $(document).find("div");
                         for(var i=0; i<items.length;i++){               
                             if(items.eq(i).attr("id")==bodyID){
                                   items.eq(i).replaceWith(viewElem);
                                   //alert(viewElem.innerHTML);
                                   //console.log("$scope.editDialogBuilder = function(model , item , type) ::: "+$scope.currentMetaDataPath);

                             } else if(items.eq(i).attr("id")==footerID){
                                 items.eq(i).replaceWith(footerDiv);
                             }//end if(items.eq(i).attr("id")=="modalbody")
                         }//end for(var i=0; i<items.length;i++) 


                         items = $(document).find("h4");
                         for(var i=0; i<items.length;i++){               
                             if(items.eq(i).attr("id")==titleID){
                                   items.eq(i).replaceWith(titleheader);
                                  // console.log("editDialogBuilderExtern :::: "+viewElem.innerHTML);                
                                  //console.log("$scope.editDialogBuilder = function(model , item , type) ::: "+$scope.currentObject.langue);          
                             } 
                         }//end for(var i=0; i<items.length;i++){
                   },function(error){
                       commonsTools.hideDialogLoading();
                       commonsTools.showMessageDialog(error);
                   });
             

         };
         /**
          * 
          * @param {type} metaData
          * @param {type} datas
          * @returns {undefined}
          */
         $scope.listDialogWithDataBuilder = function(model , metaData , datas , index){
//            $scope.innerWindowType = true;
            $scope.dataCache[metaData.entityName] = datas;
           //Chargement des donn�es
//           console.log("editDialogBuilder ==== nbre = "+datas.length+" == meta :"+angular.toJson(metaData));    
            //Traitement du currentObject       
               //Affeectation du model dans l'object temporaire              
             var viewElem =  document.createElement('div'); //;  
             var bodyID = "";
             var endIndex = index+1;
             if(endIndex==1){
                 bodyID="modalbody";
             }else if(endIndex==2){
                  bodyID="gmodalbody";
             }else if(endIndex==2){
                  bodyID="modalbody1";
             }else if(endIndex==2){
                  bodyID="modalbody2";
             }
             viewElem.setAttribute('id' , bodyID);
             //Construction de la partie recherche
             var editPanel =  $scope.listPanelDialogComponent(metaData,index);
             var bodyElem = document.createElement('div');
             bodyElem.setAttribute('style','overflow: auto;margin-top: -20px;');
             bodyElem.appendChild(editPanel);
             viewElem.appendChild(bodyElem);           
             //Construction du footer
             var footerDiv = document.createElement('div');
             footerDiv.setAttribute('class' , 'modal-footer');
             var footerID = "";
             if(endIndex==1){
                 footerID="modalfooter";
             }else if(endIndex==2){
                  footerID="gmodalfooter";
             }else if(endIndex==2){
                  footerID="modalfooter1";
             }else if(endIndex==2){
                  footerID="modalfooter2";
             }
             footerDiv.setAttribute('id' , footerID);
             var buttonElem = document.createElement('button');
             footerDiv.appendChild(buttonElem);
             buttonElem.setAttribute('class' , 'btn btn-primary');
             buttonElem.setAttribute('ng-click' , "addDialogAction('"+model+"' , 'follower','"+metaData.entityName+"' , '"+metaData.moduleName+"',null,"+(index+1)+")");
             buttonElem.appendChild(document.createTextNode('Save Change'));
             //Button annuler
             buttonElem = document.createElement('button');
             footerDiv.appendChild(buttonElem);
             buttonElem.setAttribute('class' , 'btn btn-default');
             buttonElem.setAttribute('data-dismiss' , "modal");
             buttonElem.setAttribute('type' , 'button');
             buttonElem.appendChild(document.createTextNode('Annuler'));
             //Entete modal
             var titleheader = document.createElement('h4');
             titleheader.setAttribute('class','modal-title');
             var titleID = "";
             if(endIndex==1){
                 titleID="modaltitle";
             }else if(endIndex==2){
                  titleID="gmodaltitle";
             }else if(endIndex==2){
                  titleID="modaltitle1";
             }else if(endIndex==2){
                  titleID="modaltitle2";
             }
             titleheader.setAttribute('id',titleID);
             titleheader.appendChild(document.createTextNode(metaData.listTitle));
              //console.log(viewElem.innerHTML);
             var compileFn = $compile(viewElem);
             compileFn($scope);
             var compileFoot = $compile(footerDiv);
             compileFoot($scope);
             var items = $(document).find("div");
             for(var i=0; i<items.length;i++){               
                 if(items.eq(i).attr("id")==bodyID){
                       items.eq(i).replaceWith(viewElem);
                       //alert(viewElem.innerHTML);
                       //console.log("$scope.editDialogBuilder = function(model , item , type) ::: "+$scope.currentMetaDataPath);

                 } else if(items.eq(i).attr("id")==footerID){
                     items.eq(i).replaceWith(footerDiv);
                 }//end if(items.eq(i).attr("id")=="modalbody")
             }//end for(var i=0; i<items.length;i++) 


             items = $(document).find("h4");
             for(var i=0; i<items.length;i++){               
                 if(items.eq(i).attr("id")==titleID){
                       items.eq(i).replaceWith(titleheader);
                      // console.log("editDialogBuilderExtern :::: "+viewElem.innerHTML);                
                      //console.log("$scope.editDialogBuilder = function(model , item , type) ::: "+$scope.currentObject.langue);          
                 } 
             }//end for(var i=0; i<items.length;i++){
                   
         };
         /**
          * 
          * @param {type} data
          * @param {type} metadata
          * @returns {undefined}
          */
        $scope.listDialogBuilderExternal = function(data , metaData,index){
//            $scope.innerWindowType = true;            
                           //Affeectation du model dans l'object temporaire     
                         $scope.temporalDatas = data;
                         var viewElem =  document.createElement('div'); //;  
                         var endIndex = index+1;
                         var bodyID = "";
                         if(endIndex==1){
                             bodyID = "modalbody";
                         }else if(endIndex==2){
                             bodyID = "gmodalbody";
                         }else if(endIndex==3){
                             bodyID = "modalbody1";
                         }else if(endIndex==4){
                             bodyID = "modalbody2";
                         }
                         viewElem.setAttribute('id' , bodyID);                         
                         //Construction de la partie recherche
                         var editPanel =  $scope.listPanelDialogComponentExter(metaData);
                         var bodyElem = document.createElement('div');
                         bodyElem.setAttribute('style','overflow: auto;margin-top: -20px;');
                         bodyElem.appendChild(editPanel);
                         viewElem.appendChild(bodyElem);           
                         //Construction du footer
                         var footerDiv = document.createElement('div');
                         footerDiv.setAttribute('class' , 'modal-footer');
                         var footerID = "";
                         if(endIndex==1){
                             footerID = "modalfooter";
                         }else if(endIndex==2){
                             footerID = "gmodalfooter";
                         }else if(endIndex==3){
                             footerID = "modalfooter1";
                         }else if(endIndex==4){
                             footerID = "modalfooter2";
                         }
                         footerDiv.setAttribute('id' , footerID);
//                         var buttonElem = document.createElement('button');
//                         footerDiv.appendChild(buttonElem);
//                         buttonElem.setAttribute('class' , 'btn btn-primary');
//                         buttonElem.setAttribute('ng-click' , "addDialogAction('"+model+"' , 'list','"+metaData.entityName+"' , '"+metaData.moduleName+"',null,"+(index+1)+")");
//                         buttonElem.appendChild(document.createTextNode('Save Change'));
                         //Button annuler
                         buttonElem = document.createElement('button');
                         footerDiv.appendChild(buttonElem);
                         buttonElem.setAttribute('class' , 'btn btn-default');
                         buttonElem.setAttribute('data-dismiss' , "modal");
                         buttonElem.setAttribute('type' , 'button');
                         buttonElem.appendChild(document.createTextNode('Annuler'));
                         //Entete modal
                         var titleheader = document.createElement('h4');
                         titleheader.setAttribute('class','modal-title');
                          var titleID = "";
                         if(endIndex==1){
                             titleID = "modaltitle";
                         }else if(endIndex==2){
                             titleID = "gmodaltitle";
                         }else if(endIndex==3){
                             titleID = "modaltitle1";
                         }else if(endIndex==4){
                             titleID = "modaltitle2";
                         }
                         titleheader.setAttribute('id',titleID);
                         titleheader.appendChild(document.createTextNode(metaData.listTitle));
                          //console.log(viewElem.innerHTML);
                         var compileFn = $compile(viewElem);
                         compileFn($scope);
                         var compileFoot = $compile(footerDiv);
                         compileFoot($scope);
                         var items = $(document).find("div");
                         for(var i=0; i<items.length;i++){               
                             if(items.eq(i).attr("id")==bodyID){
                                   items.eq(i).replaceWith(viewElem);
                                   //alert(viewElem.innerHTML);
                                   //console.log("$scope.editDialogBuilder = function(model , item , type) ::: "+$scope.currentMetaDataPath);

                             } else if(items.eq(i).attr("id")==footerID){
                                 items.eq(i).replaceWith(footerDiv);
                             } 
                         } 


                         items = $(document).find("h4");
                         for(var i=0; i<items.length;i++){               
                             if(items.eq(i).attr("id")==titleID){
                                   items.eq(i).replaceWith(titleheader);
                                  // console.log("editDialogBuilderExtern :::: "+viewElem.innerHTML);                
                                  //console.log("$scope.editDialogBuilder = function(model , item , type) ::: "+$scope.currentObject.langue);          
                             } 
                         }//end for(var i=0; i<items.length;i++){                   
             

         };
        /**
          Affichage de la fenetre Dialog pour l'edition d'une entite
          @metaData : MetaData deecrivant la structure de l'entit�
        **/
        $scope.editDialogBuilderExtern = function(metaData,index){           
//           $scope.innerWindowType = true;
           $scope.innerWindowType = 'new';
           var viewElem =  document.createElement('div'); //;
           var bodyID = "";
           var endIndex = index;
           if(endIndex==1){
               bodyID="modalbody";
           }else if(endIndex==2){
               bodyID="gmodalbody";
           }else if(endIndex==3){
               bodyID="modalbody1";
           }else if(endIndex==4){
               bodyID="modalbody2";
           }
           viewElem.setAttribute('id' , bodyID);
           viewElem.setAttribute("class","modal-body");
           viewElem.setAttribute("style","height:100%;");
           var headerElem = $scope.editPanelHeader('temporalData' , metaData,index);  
           var editPanel =  $scope.editPanelComponent('temporalData',metaData,'new',index);
            if(headerElem){
               viewElem.appendChild(headerElem);
           }//end if(headerElem)
           //viewElem.appendChild(headerElem);
           viewElem.appendChild(editPanel);
           //Construction du footer
           var footerDiv = document.createElement('div');
           footerDiv.setAttribute('class' , 'modal-footer');
           var footerID = "";
           if(endIndex==1){
               footerID="modalfooter";
           }else if(endIndex==2){
               footerID="gmodalfooter";
           }else if(endIndex==3){
               footerID="modalfooter1";
           }else if(endIndex==4){
               footerID="modalfooter2";
           }
           footerDiv.setAttribute('id' , footerID);
           if($scope.canUpdate()||$scope.canCreate()){
                var buttonElem = document.createElement('button');
                footerDiv.appendChild(buttonElem);
                buttonElem.setAttribute('class' , 'btn btn-primary');
                buttonElem.setAttribute('ng-click' , "addDialogAction('temporalData' , 'save_only','"+metaData.entityName+"' , '"+metaData.moduleName+"',null,"+(index+1)+")");
                buttonElem.appendChild(document.createTextNode('Valider'));
            }//end if($scope.windowType!='view'){ 
                     
           //Button annuler
           buttonElem = document.createElement('button');
           footerDiv.appendChild(buttonElem);
           buttonElem.setAttribute('class' , 'btn btn-default');
           buttonElem.setAttribute('data-dismiss' , "modal");
           buttonElem.setAttribute('type' , 'button');
           buttonElem.setAttribute('ng-click' , 'annulerAction2()');
           buttonElem.appendChild(document.createTextNode('Annuler'));
           //Entete modal
           var titleheader = document.createElement('h4');
           titleheader.setAttribute('class','modal-title');
           var titleID = "";
           if(endIndex==1){
               titleID="modaltitle";
           }else if(endIndex==2){
               titleID="gmodaltitle";
           }else if(endIndex==3){
               titleID="gmodaltitle1";
           }else if(endIndex==4){
               titleID="modaltitle2";
           }
           titleheader.setAttribute('id',titleID);
           titleheader.appendChild(document.createTextNode(metaData.editTitle));
            //console.log(viewElem.innerHTML);
           var compileFn = $compile(viewElem);
           compileFn($scope);
           var compileFoot = $compile(footerDiv);
           compileFoot($scope);
           var items = $(document).find("div");
           for(var i=0; i<items.length;i++){               
               if(items.eq(i).attr("id")==bodyID){
                     items.eq(i).replaceWith(viewElem);
                      //console.log("$scope.editDialogBuilder = function(model , item , type) ::: "+$scope.currentMetaDataPath);
          
               } else if(items.eq(i).attr("id")==footerID){
                   items.eq(i).replaceWith(footerDiv);
               } 
           } 

           
           items = $(document).find("h4");
           for(var i=0; i<items.length;i++){               
               if(items.eq(i).attr("id")==titleID){
                     items.eq(i).replaceWith(titleheader);
                    // console.log("editDialogBuilderExtern :::: "+viewElem.innerHTML);                
                    //console.log("$scope.editDialogBuilder = function(model , item , type) ::: "+$scope.currentObject.langue);          
               } 
           } 
            $timeout(function() {
                  $('.selectpicker').selectpicker('refresh');              
              });
          
        };
        
        /**
         * 
         * @param {type} model
         * @param {type} item
         * @returns {undefined}
         */
        $scope.removeFromTable = function(model , item,modelpath){
            var parts = model.split(".");            
            if(parts.length>1){
                $scope.removeFromArray($scope.currentObject[parts[1]],item);
                 var metaData = $scope.getCurrentMetaData(model) ;
                //console.log("editDialogBuilder  =====  ************* Vous avez cliquez :::: "+model+" ***** "+item+" ==== "+type+" ::: "+entityName+" ::: "+moduleName+" == "+angular.toJson(metaData));
                if(!metaData){
                   var exprFn = $parse($scope.currentMetaDataPath);     
                   metaData = exprFn($scope); 
                }
                //Evenement de construction du pied de tableau
                $rootScope.$broadcast("tablefooter" , {metaData:metaData,model:model,modelpath:modelpath});  
//                var footerElem = commonsTools.sumFooterTableBuilder(metaData ,$scope.currentObject,model);
//                var sources =model.split(".");
//                 ///Remplacement dans la vue
//                var items = $element.find("tfoot");
//                for(var i=0; i<items.length;i++){
//
//                     if(items.eq(i).attr("id")==sources[1]){
//                           items.eq(i).replaceWith(footerElem);
//                     }  
//                }
            }
            //console.log("$scope.removeFromTable = "+model+" === "+angular.toJson(item));
        };
    
    /**
     * 
     * @returns {undefined}
     */
        $scope.cancelDialogAction = function(){
            $scope.currentObject = $scope.dataCache['currentObject'];
            $scope.currentAction = $scope.dataCache['currentAction'];
            $scope.innerWindowType = false;
            //console.log("$scope.cancelDialogAction = ======== ");
            $('#myModal').modal('hide');            
        };
        
        /**
          Action d'ajout dans le tabeau
        **/
        $scope.addDialogAction = function(model , type,entityName , moduleName,customfooter,index,modelpath){  
//           console.log("$scope.addDialogAction ===== model:"+model+" type:"+type+" entity:"+entityName+" module:"+moduleName+"  index:"+index+" ::: modelpath:"+modelpath);
           $scope.innerWindowType = false;
           if($scope.windowType=="report"){
               var report = $scope.dataCache["report"];
               var url = 'http://'+$location.host()+':'+$location.port()+'/'+angular.lowercase(report.model)+'/'+angular.lowercase(report.entity)+'/'+report.method;
               commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%");        
//               $http.defaults.headers.common['args']= angular.toJson($scope.temporalData);
//$http.get(url, {responseType: 'arraybuffer',data:angular.toJson($scope.temporalData)})
               if(report.extern==false){
                   $http.put(url,$scope.temporalData)
                           .then(function(response){
                               $scope.temporalDatas = response.data;   
//                               console.log("$scope.addDialogAction ========= "+angular.toJson($scope.temporalDatas));
                               commonsTools.hideDialogLoading();
                               $scope.displayReportPanel(report.script);  
                           },function(error){
                               commonsTools.showMessageDialog(error);
                               commonsTools.hideDialogLoading();
                           });
               }else{
                     $http.put(url,$scope.temporalData, {responseType: 'arraybuffer'})
                       .then(function(response){
                              var contentElem = $scope.viewSelector("report");
//                               console.log(angular.toJson("$scope.addDialogAction ====== "+angular.toJson(response)));
                               var viewer = document.createElement("iframe");
                               viewer.setAttribute("id","iframe0001");
                               viewer.setAttribute("src",url);
                               viewer.setAttribute("alt","pdf");
                               viewer.setAttribute("width","100%");
                               viewer.setAttribute("height","700px");
//                               viewer.setAttribute("pluginspage","http://www.adobe.com/products/acrobat/readstep2.html");
//                               viewer.setAttribute("class","ng-isolate-scope");
                               var divElem = document.createElement("div");
                               divElem.setAttribute("id","report");
                               divElem.setAttribute("width","100%");
                               divElem.setAttribute("height","100%");
                               divElem.appendChild(viewer);
                               var items = contentElem.find('div');
                                for(var i=0; i<items.length;i++){
                                   if(items.eq(i).attr("id")=="report"){
                                         items.eq(i).replaceWith(divElem);                               
                                   }  
                               }//enn$d for(var i=0; i<items.length;i++){                               
                               // ///Remplacement dans la vue
                              var items = $element.find("div");
                              for(var i=0; i<items.length;i++){
                                   if(items.eq(i).attr("id")=="innerpanel"){
                                         items.eq(i).replaceWith(contentElem);
                                          //console.log(" ======================= on a trouve report  innerpanel");
                                   }//end if(items.eq(i).attr("id")=="innerpanel")  
                              }//end for(var i=0; i<items.length;i++)
                               var compileFn = $compile(contentElem);
                               compileFn($scope);                              
                                var arrayBufferView = new Uint8Array(response.data );
                                var blob = new Blob( [ arrayBufferView ], { type: "application/pdf" } );
                                var urlCreator = window.URL || window.webkitURL;
                                var pdfUrl = urlCreator.createObjectURL( blob );
                                var pdf = document.querySelector( "#iframe0001");
                                pdf.src = pdfUrl;
//                               console.log($scope.temporalData);                      
                                commonsTools.hideDialogLoading();
                       },function(error){
                           commonsTools.showMessageDialog(error);
                           commonsTools.hideDialogLoading();
                       });
               }//end if(report.extern==false)
              
//               console.log("Critere de recherh=c*************** "+$scope.dataCache["report"]+" === "+angular.toJson($scope.temporalData));
               $("#globalModal").modal("hide");
           }else//end if($scope.windowType=="report")
           if(type=="new"){ 
                 //$scope.displayEditPanel();
                 var part = model.split(".");
                  var key = commonsTools.keygenerator(modelpath);
                  if(part[0]=='currentObject'){                     
                     $scope.temporalModel = $scope.getCurrentModel(model);
                     $scope.temporalModel.unshift($scope.dataCache[""+key]);
                 }else{
                     $scope.temporalModel = $scope.getCurrentModel(model);           
                     $scope.temporalModel[part[part.length-1]].unshift($scope.dataCache[""+key]);
                 }//end  if(part[0]=='currentObject')                 
//                 console.log("addDialogBuilder  =====  ************* Vous avez cliquez :::: "+model+" ***** "+key+" ==== "+angular.toJson($scope.temporalModel)+" === "+angular.toJson($scope.dataCache[""+key]));
                 var metaData = $scope.getCurrentMetaData(modelpath) ;
                 var sources = model.split('.');
                if(!metaData){
                   var exprFn = $parse($scope.currentMetaDataPath);     
                   metaData = exprFn($scope); 
                }
                //Evenement de construction du pied de tableau
                $rootScope.$broadcast("tablefooter" , {metaData:metaData,model:model,modelpath:modelpath});  
                
            }else if(type=="update"){
                var key = commonsTools.keygenerator(modelpath);
//                $scope.innerWindowType = true;
                var item = $scope.temporalDatas.pop(); 
                angular.extend(item ,$scope.dataCache[""+key]);   //angular.extend(item , $scope.temporalData);   
                var metaData = $scope.getCurrentMetaData(modelpath) ;
                if(!metaData){
                   var exprFn = $parse($scope.currentMetaDataPath);     
                   metaData = exprFn($scope); 
                }
                 //Evenement de construction du pied de tableau
                $rootScope.$broadcast("tablefooter" , {metaData:metaData,model:model,modelpath:modelpath});  

            }else if(type=="new_entity"){
                var key = commonsTools.keygenerator(modelpath);
                $scope.saveanrelaod(model,$scope.dataCache[""+key],entityName,moduleName);                
            }else if(type=='save_only'){
                $scope.saveonly(model,$scope.temporalData,entityName,moduleName);   
//                $("#globalModal").modal("hide");
            }else if(type=="list"){
                var parts = model.split(".");
                var key = commonsTools.keygenerator(model);
                if(parts.length>1){      
                     var metaData = $scope.getCurrentMetaData(model) ;
                     var col =parts.length-1;
                    //Ajout des donn�es
                    var items = $scope.dataCache.selectedObjects;
                    $scope.dataCache[""+key+""] = new Array();
                    var templateModel = $scope.getCurrentModel(model);
                    if(parts[0]=='currentObject'){
                        templateModel = $scope.currentObject;
                    }//end if(parts[0]=='currentObject')
//                    console.log("$scope.addDialogAction =list ===== model: "+model+"===== "+items.length+" === "+parts[0]+" === "+parts[1]+" == modelpath:"+modelpath+"  template:"+angular.toJson(templateModel));
                    if(angular.isArray(templateModel[parts[col]])){
                        if(!templateModel[parts[col]]){
                            templateModel[parts[col]] = new Array();
                        }
                        for(var i=0 ;i<items.length;i++){                        
                            if(!$scope.containsObject(templateModel,items[i])){
                                templateModel[parts[col]].push(items[i]);
                                $scope.dataCache[""+key+""].push(items[i]);
                            }//end if(!$scope.containsObject($scope.currentObject[parts[1]],items[i]))
                        }//end for(var i=0 ;i<items.length;i++)
                    }else{
                        if(items.length>0){
                            templateModel[parts[col]] = items[0];
                            $scope.dataCache[""+key+""].push(items[0]);
                        }
                    }//end if(angular.isArray($scope.currentObject[parts[1]]))
                    var obj = {id:'load' , designation:'Charger les données ....'};
                    $scope.dataCache[""+key+""].push(obj);
                     //Evenement de construction du pied de tableau
//                    $rootScope.$broadcast("tablefooter" , {metaData:metaData,model:model});  
                }//end if(parts.length>1)
            }else if(type=="follower"){                
//                console.log("$scope.addDialogAction ==== "+$scope.dataCache[entityName].length+" === "+type+" === "+entityName);
                if($scope.dataCache[entityName].length>0){
                    for(var i=0;i<$scope.dataCache[entityName].length;i++){
                        if(angular.lowercase(entityName)=="utilisateur"){
                            $scope.dataCache['follower'].abonnes.push($scope.dataCache[entityName][i]);
                        }else if(angular.lowercase(entityName)=="canal"){
                            $scope.dataCache['follower'].canaux.push($scope.dataCache[entityName][i]);
                        }//end if(angular.lowercase(entityName)=="utilisateur")
                    }//end for(var i=0;i<$scope.dataCache[entityName].length;i++)
                    
                    var url = "http://"+$location.host()+":"+$location.port()+"/kerencore/follower";
                    if($scope.dataCache['follower'].id==-1){
                        $http.post(url,$scope.dataCache['follower'])
                                .then(function(response){
                                        var url = "http://"+$location.host()+":"+$location.port()+"/kerencore/follower/entity/"+$scope.currentObject.serial+"/"+$scope.currentObject.id;
                                        $http.get(url)
                                                .then(function(response){
                                                    $scope.dataCache['follower'] = response.data;  
                                                    $scope.buildfollowermenu();
                                                },function(error){
                                                    commonsTools.showMessageDialog(error);
                                                });
                                },function(error){
                                    commonsTools.showMessageDialog(error);
                                });
                    }else{
                        
                        $http.put(url+"/"+$scope.dataCache['follower'].id,$scope.dataCache['follower'])
                                .then(function(response){
                                     var url = "http://"+$location.host()+":"+$location.port()+"/kerencore/follower/entity/"+$scope.currentObject.serial+"/"+$scope.currentObject.id;
                                    $http.get(url)
                                            .then(function(response){
                                                $scope.dataCache['follower'] = response.data;  
                                                $scope.buildfollowermenu();
                                            },function(error){
                                                commonsTools.showMessageDialog(error);
                                            });
                                },function(error){
                                    commonsTools.showMessageDialog(error);
                                });
                    }//end if($scope.dataCache['follower'].id==-1)
                }//end if($scope.dataCache[entityName].length>0)
            }            
            $timeout(function() {
                $('.selectpicker').selectpicker('refresh');

            });
            var modalID = "";
            var endIndex = index-1;            
            if(endIndex==1){
                modalID = "myModal";
            }else if(endIndex==2){
                modalID = "globalModal";
            }else if(endIndex==3){
                modalID = "myModal1";
            }else if(endIndex==4){
                modalID = "myModal2";
            }
            $('#'+modalID).modal('hide');            
        };
        
                
       /***
        * 
        */
        $scope.$on("tablefooter" , function(event , args){
              var sources =args.model.split(".");    
               var key = commonsTools.keygenerator(args.modelpath);
               var footerElem = null ;
               var data = $scope.currentObject;
                if(sources[0]!="currentObject"){
                    data = $scope.dataCache[""+key];                    
                }//end if(sources[0]!="currentObject"){
                data = data[sources[sources.length-1]];
                if($scope.dataCache[key+"foot"]){                   
                    footerElem = commonsTools.tableFooterBuilder($scope.dataCache[key+"foot"],data,key);
//                     console.log("tablefooter  =====  ************* Vous avez cliquez :::: "+args.model+" === "+args.modelpath+" === "+angular.toJson(data));
                }else{
                    footerElem = commonsTools.sumFooterTableBuilder(args.metaData ,data,args.model,key);
                }//end if($scope.dataCache[sources[1]])
                ///Remplacement dans la vue
                var items = $element.find("tfoot");
                for(var i=0; i<items.length;i++){
                     if(items.eq(i).attr("id")==key){
                           items.eq(i).replaceWith(footerElem);
                     }  
                }
                $scope.dataCache[key] = null;
           });

       /**
        * 
        * @param {type} columns
        * @returns {undefined}
        */
        $scope.createFromFields = function(columns){
              for(var i=0 ; i< columns.length;i++){
                   if(columns[i].type=='object'){
                     if(!$scope.currentObject[""+columns[i].fieldName+""]){
                       $scope.currentObject[""+columns[i].fieldName+""] = null;
                     }
                   }else if(columns[i].type=='array'){
                      if(!$scope.currentObject[""+columns[i].fieldName+""]) {
                         $scope.currentObject[columns[i].fieldName] = new Array();
                       }
                       //console.log("createFromFields =====  "+$scope.currentObject[columns[i].fieldName]);                       
                           
                       //object["'"+metaData.columns[i].fieldName+"'"].push($scope.createEmptyObject(metaData.columns[i].metaData));
                   }else{
                       if(!$scope.currentObject[""+columns[i].fieldName+""]) {
                         $scope.currentObject[""+columns[i].fieldName+""] = "";
                       }
                   }
               }
        };
        /**
         * 
         * @param {type} entity
         * @param {type} columns
         * @returns {undefined}
         */
        $scope.createFreeFromFields = function(entity , columns){
              for(var i=0 ; i< columns.length;i++){
                   if(columns[i].type=='object'){
                     if(!entity[""+columns[i].fieldName+""]){
                       entity[""+columns[i].fieldName+""] = null;
                     }
                   }else if(columns[i].type=='array'){
                      if(!entity[""+columns[i].fieldName+""]) {
                         entity[columns[i].fieldName] = new Array();
                       }
                       //console.log("createFromFields =====  "+$scope.currentObject[columns[i].fieldName]);                       
                           
                       //object["'"+metaData.columns[i].fieldName+"'"].push($scope.createEmptyObject(metaData.columns[i].metaData));
                   }else{
                       if(!entity[""+columns[i].fieldName+""]) {
                         entity[""+columns[i].fieldName+""] = "";
                       }
                   }
               }
        };

        /**
         Create a empty object base of the metaData
         @metaData : the description of the object
      **/
      $scope.createEmptyObject  = function(metaData){            
             //var object = new Object();

             if(metaData){
                //Cas des colonnes
                if(metaData.columns){
                      $scope.createFromFields(metaData.columns);                     
                }
                //Traitement des champs groups
                if(metaData.groups){
                     //Traitement des groups
                     for(var i=0 ; i< metaData.groups.length;i++){
                         //Cas des colonnes
                         if(metaData.groups[i].columns){
                            $scope.createFromFields(metaData.groups[i].columns);
                         }
                         //cas des metaArray
                         if(metaData.groups[i].metaArray){
                             $scope.currentObject[metaData.groups[i].metaArray.fieldName] = new Array();
                         }
                     }
                }

             } 
            
         };
         /**
          * 
          * @param {type} metaData
          * @returns {undefined}
          */
         $scope.createFreeEmptyObject  = function(metaData){            
             var entity = new Object();
             if(metaData){
                //Cas des colonnes
                if(metaData.columns){
                      $scope.createFreeFromFields(entity , metaData.columns);                     
                }
                //Traitement des champs groups
                if(metaData.groups){
                     //Traitement des groups
                     for(var i=0 ; i< metaData.groups.length;i++){
                         //Cas des colonnes
                         if(metaData.groups[i].columns){
                            $scope.createFreeFromFields(entity , metaData.groups[i].columns);
                         }
                         //cas des metaArray
                         if(metaData.groups[i].metaArray){
                             entity[metaData.groups[i].metaArray.fieldName] = new Array();
                         }
                     }
                }

             } 
            return entity ;
         };
         /**
          * 
          * @param {type} columns
          * @returns {undefined}
          */
        $scope.createTemporalFromFields = function(columns,template){               
              for(var i=0 ; i< columns.length;i++){
//                  console.log("createFromFields =====  "+columns[i].type+"  === "+angular.toJson(template)); 
                   if(columns[i].type=='object'){
                     if(!$scope.temporalData[""+columns[i].fieldName+""]){
                       if(template==null||!angular.isDefined(template[""+columns[i].fieldName+""])){
                            $scope.temporalData[""+columns[i].fieldName+""] = null;
                        }else{
                            $scope.temporalData[""+columns[i].fieldName+""] = template[""+columns[i].fieldName+""];
                        }//end if(template==null||!angular.isDefined(template[""+columns[i].fieldName+""]))
                     }//end if(!$scope.temporalData[""+columns[i].fieldName+""])                     
                   }else if(columns[i].type=='array'){                     
                      if(!$scope.temporalData[""+columns[i].fieldName+""]) {
                          if(!$scope.temporalData[""+columns[i].fieldName+""]) {
                                if(template==null||!angular.isDefined(template[""+columns[i].fieldName+""])){
                                    $scope.temporalData[columns[i].fieldName] = new Array();
                                }else{
                                    $scope.temporalData[columns[i].fieldName] =  new Array();
                                    if(angular.isArray(template[""+columns[i].fieldName+""])){
                                        $scope.temporalData[columns[i].fieldName] =  template[""+columns[i].fieldName+""];
                                    }else{
                                        $scope.temporalData[columns[i].fieldName].push(template[""+columns[i].fieldName+""]);
                                    }//end if(angular.isArray(template[""+columns[i].fieldName+""])){
                                    
                                }//end if(template==null||!angular.isDefined(template[""+columns[i].fieldName+""]))
                          }//end if(!$scope.temporalData[""+columns[i].fieldName+""]) 
                       }                                         
                           
                       //object["'"+metaData.columns[i].fieldName+"'"].push($scope.createEmptyObject(metaData.columns[i].metaData));
                   }else{
                       if(!$scope.temporalData[""+columns[i].fieldName+""]) {
                            if(template==null||!angular.isDefined(template[""+columns[i].fieldName+""])){
                                $scope.temporalData[""+columns[i].fieldName+""] = "";
                            }else{
                                $scope.temporalData[""+columns[i].fieldName+""] = template[""+columns[i].fieldName+""];
                            }//end if(template==null||!angular.isDefined(template[""+columns[i].fieldName+""]))
                       }//end if(!$scope.temporalData[""+columns[i].fieldName+""])
                   }
               }
        };
           /**
         Create a empty object base of the metaData
         @metaData : the description of the object
      **/
      $scope.createEmptyTemporalObject  = function(metaData,template){            
             //var object = new Object();

             if(metaData){
                //Cas des colonnes
                if(metaData.columns){
                      $scope.createTemporalFromFields(metaData.columns,template);                     
                }
                //Traitement des champs groups
                if(metaData.groups){
                     //Traitement des groups
                     for(var i=0 ; i< metaData.groups.length;i++){
                         //Cas des colonnes
                         if(metaData.groups[i].columns){
                            $scope.createTemporalFromFields(metaData.groups[i].columns,template);
                         }
//                         $scope.temporalData[columns[i].fieldName] =  new Array();                        
                         //cas des metaArray
                         if(metaData.groups[i].metaArray){
                             $scope.temporalData[metaData.groups[i].metaArray.fieldName] = new Array();
                             if(angular.isArray(template[""+metaData.groups[i].metaArray.fieldName+""])){
                                $scope.temporalData[metaData.groups[i].metaArray.fieldName] =  template[""+metaData.groups[i].metaArray.fieldName+""];
                             }else{
                                $scope.temporalData[metaData.groups[i].metaArray.fieldName].push(template[""+metaData.groups[i].metaArray.fieldName+""]);
                             }//end if(angular.isArray(template[""+columns[i].fieldName+""])){
                         }//end if(metaData.groups[i].metaArray){
                     }
                }

             } 
            
         };

         /**
          * 
          * @param {type} title
          * @param {type} message
          * @param {type} type
          * @returns {undefined}
          */
         $scope.notifyWindow = function(title , message ,type){
            $.notify(
              {
                title: "<strong>"+title+":</strong> ",
                message: message
                
              },{
                 type:type,
                  animate: {
                    enter: 'animated fadeInRight',
                    exit: 'animated fadeOutRight'
                  }
                 
              }
            );
         };


       $scope.uniqueContraints = [];
       /**
         Validate all the fields of the forms to chack constraint validation
          
       **/
     
      /**
       * 
       * @returns {Array}
       */
       $scope.validateFields = function(){
            var champs = new Array();
            $scope.uniqueContraints =  new Array();
            if($scope.metaData && $scope.currentObject){
                if($scope.metaData.columns){
                   for(var i=0 ; i<$scope.metaData.columns.length;i++){
                       if(!$scope.metaData.columns[i].optional || $scope.metaData.columns[i].min){
                           if(!$scope.currentObject[$scope.metaData.columns[i].fieldName]){
                               champs.push($scope.metaData.columns[i].fieldLabel);
                           }
                       }
                       //Construction des champs pour unicite
                       if($scope.metaData.columns[i].unique){
                           var pred = new Object();
                           pred.fieldLabel = $scope.metaData.columns[i].fieldLabel;
                           pred.fieldName = $scope.metaData.columns[i].fieldName;
                           pred.fieldValue = $scope.currentObject[$scope.metaData.columns[i].fieldName];
                           $scope.uniqueContraints.push(pred);
                       }
                   }
                 }
                 //Cas des groups
                 if($scope.metaData.groups){
                    for(var i=0;i<$scope.metaData.groups.length;i++){
//                        if($scope.metaData.groups[i].metaArray){
//                            for(var j=0 ; j<$scope.metaData.groups[i].metaArray.metaData.columns.length ; j++){
//                                if(!$scope.metaData.groups[i].metaArray.metaData.columns[j].optional || $scope.metaData.groups[i].metaArray.metaData.columns[j].min){
//                                      champs.push($scope.metaData.groups[i].metaArray.metaData.columns[j].fieldLabel);
//                                }
//                            }
//                        }
                        //Cas des données normales
                        if($scope.metaData.groups[i].columns){
                           for(var j=0 ; j<$scope.metaData.groups[i].columns.length;j++){
                              if(!$scope.metaData.groups[i].columns[j].optional || $scope.metaData.groups[i].columns[j].min){
                                   var pred = new Object();
                                    pred.fieldLabel = $scope.metaData.groups[i].columns[j].fieldLabel;
                                    pred.fieldName = $scope.metaData.groups[i].columns[j].fieldName;
                                    pred.value = $scope.currentObject[$scope.metaData.groups[i].columns[j].fieldName];
                                    $scope.uniqueContraints.push(pred);
                                    // champs.push($scope.metaData.groups[i].columns[j].fieldLabel);
                              }
                              //Construction des champs pour unicite
                            if($scope.metaData.groups[i].columns[j].unique){
                                $scope.uniqueContraints.push($scope.metaData.groups[i].columns[j].fieldLabel);
                            }
                           }
                        }
                    }
                 }
            }
            return champs;

       };

        /**
       * 
       * @param {type} name:Identifiant de la modele de rapport
       * @returns {undefined}
       */
      $scope.customPrintAction = function(id){
          $scope.showreporttitle = true;
          if(id){
              commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%"); 
              var url='http://'+$location.host()+":"+$location.port()+"/kerencore/reportrecord/byid/id/"+id;
              $http.get(url)
                      .then(function(response){
                          var report = response.data;
                          $scope.windowType = "report";
                          if(report.model&&report.entity){
                                var url_1 = 'http://'+$location.host()+":"+$location.port()+"/"+angular.lowercase(report.model)+"/"+angular.lowercase(report.entity)+"/meta";
                                $http.get(url_1)
                                        .then(function(response){
                                              var meta = response.data;
                                              $rootScope.$broadcast("customreport" , {metaData:meta,report:report});      
//                                              console.log(angular.toJson(meta)+" === "+report.model+" === "+report.entity);                                              
                                              commonsTools.hideDialogLoading();
                                        },function(error){
                                            commonsTools.hideDialogLoading();
                                            commonsTools.showMessageDialog(error);
                                        });
                          }else{
                              $scope.displayReportPanel(report.script);
                              commonsTools.hideDialogLoading();
                          }//end if(report.model&&report.entity)                          
                      },function(error){
                          commonsTools.hideDialogLoading();
                          commonsTools.showMessageDialog(error);
                      });
          }//end if(name)
      };
      
       /**  
               Chargement des donnees 
               Rafresh the data from the data store
       **/
       $scope.loadData = function(){
           //Chargement des donnees
                //restService.url('societe');
//               console.log('$scope.loadData = function() ::::::::::::::::'+$scope.pagination.currentPage+"==== "+$scope.pagination.totalPages+" ==== "+angular.isNumber($scope.pagination.totalPages));
               try{   var pageBeginIndex = $scope.pagination.currentPage - $scope.pagination.pageSize;
                      if(pageBeginIndex<0){
                          pageBeginIndex = 0;
                      }                   
                      restService.filter($scope.predicats ,pageBeginIndex , $scope.pagination.pageSize)
                               .$promise.then(function(datas){                                    
                                    if(datas){
                                        $scope.datas = datas;
                                //Traitement des données
                                       if($scope.calendarrecord){
                                            for(var i=0;i<datas.length;i++){
                                               var data = datas[i];
                                               if($scope.calendarrecord.titlefield){
                                                   data['title'] = data[$scope.calendarrecord.titlefield];
                                               }
                                               if($scope.calendarrecord.startfield){
                                                   data['start'] = data[$scope.calendarrecord.startfield];
                                               }
                                               if($scope.calendarrecord.endfield){
                                                   data['end'] = data[$scope.calendarrecord.endfield];
                                               }
                                               data['allDay'] = $scope.calendarrecord.allday;
                                            }//end for(var i=0;i<datas.length;i++){
                                            $scope.eventSources = [datas];
                                        }//end if($scope.calendarrecord)
                                        if($scope.pagination.beginIndex<=0){
                                            $scope.pagination.endIndex = $scope.pagination.pageSize;
                                            if($scope.pagination.totalPages<=$scope.pagination.pageSize){
                                                $scope.pagination.endIndex=$scope.pagination.totalPages;
                                            }
                                        }
                                        $scope.pagination.hasnext();
                                        $scope.pagination.hasprevious();
                                        $rootScope.$broadcast("dataLoad" , {
                                            message:"dataLoad"
                                        });
                                        commonsTools.hideDialogLoading();
                                    }
                               } ,function(error){
                                   commonsTools.hideDialogLoading();
                                   commonsTools.showMessageDialog(error);
                               });  
                      
//                      for(var i=0 ; i<$scope.predicats.length;i++){
//                         console.log($scope.predicats[i].fieldName+" ==== "+$scope.predicats[i].fieldValue);
//                      }
               }catch(ex){
                    commonsTools.hideDialogLoading();
                    commonsTools.notifyWindow("Une erreur est servenu pendant le traitement" ,"<br/>"+ex.message,"danger");
                }              
       };
       
       /**
        * 
        * @param {type} model
        * @param {type} item
        * @param {type} entityName
        * @param {type} moduleName
        * @returns {undefined}
        */
       $scope.saveonly = function(model ,item ,entityName,moduleName){
           //console.log("$scope.saveonly = function(model ,item ,entityName,moduleName) ==== "+angular.toJson(item));
           commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%"); 
           urlPath = "http://"+$location.host()+":"+$location.port()+"/"+angular.lowercase(moduleName)+"/"+angular.lowercase(entityName)+"/";
           $http.post(urlPath,item).then(
                function(response){
                   commonsTools.hideDialogLoading();
                   $scope.notifyWindow("Status Operation" ,"L'opération s'est déroulée avec sucess","success");                   
                },
              function(error){
               commonsTools.hideDialogLoading();
               commonsTools.showMessageDialog(error);
           } );
       };
       
       /**
        * Sauvegarde une entity et recharge 
        * @param {type} model
        * @param {type} item
        * @param {type} entityName
        * @param {type} moduleName
        * @returns {undefined}
        */
       $scope.saveanrelaod = function(model ,item ,entityName,moduleName){
           $scope.showDialogLoading("Chargement ...","white","#9370db","0%","0%"); 
           urlPath = "http://"+$location.host()+":"+$location.port()+"/"+angular.lowercase(moduleName)+"/"+angular.lowercase(entityName)+"/";
           $http.post(urlPath,item).then(
                function(response){
                   urlPath = "http://"+$location.host()+":"+$location.port()+"/"+angular.lowercase(moduleName)+"/"+angular.lowercase(entityName)+"/";
                   $http.get(urlPath).then( 
                      function(response){
                           //alert("Data: "+data+" \nStatus: "+status);   
                           var parts = model.split(".");
                           var key = commonsTools.keygenerator(model);
                           if(parts.length>1){
                               $scope.dataCache[""+key+""] = new Array();
                               //var data = $scope.dataCache[""+parts[1]+""];
                               for(var i=0;i<response.data.length;i++){
                                    $scope.dataCache[""+key+""].push(response.data[i]);
                                }                         
                           }
                            $timeout(function() {
                                $('.selectpicker').selectpicker('refresh');

                            });
                           commonsTools.hideDialogLoading();
                     },
                     function(error){
                        commonsTools.hideDialogLoading();
                        commonsTools.showMessageDialog(error);
                   } );
              },
              function(error){
               $scope.hideDialogLoading();
               commonsTools.showMessageDialog(error);
           } );
           //console.log("$scope.getData ===      "+model+" === "+entityName+" ==== "+moduleName+" === "+item);
       };
        /**
         * 
         * @param {type} model
         * @param {type} item
         * @param {type} entityName
         * @param {type} moduleName
         * @returns {undefined}
         */
        $scope.getData = function(model ,item ,entityName,moduleName,index){
//            console.log("$scope.getData ===      "+model+" ==== "+item+" === "+entityName+" ==== "+moduleName+" === "+index);
            var url = "http://"+$location.host()+":"+$location.port()+"/"+angular.lowercase(moduleName)+"/"+angular.lowercase(entityName)+"/count";
            $http.get(url)
                    .then(function(response){
                        var data = response.data;
                         if(data.value<=5){
                               var parts = model.split(".");
                                if(parts.length>1){
                                    var key = commonsTools.keygenerator(model);
                                    var array = $scope.dataCache[key];
                                    var obj = array[array.length-1];               
                                    if(obj.id == "load"){       
                                        $scope.showDialogLoading("Chargement ...","white","#9370db","0%","0%");  
                                         //Chargement des donn�es
                                         var urlPath = "http://"+$location.host()+":"+$location.port()+"/"+angular.lowercase(moduleName)+"/"+angular.lowercase(entityName)+"/filter/0/10";
                                         $http.get(urlPath).then( 
                                           function(response){
                                                //alert("Data: "+response.data+" === "+model);   
                                                var parts = model.split(".");
                                                if(parts.length>1){
                                                    $scope.dataCache[key] = new Array();
                                                    //var data = $scope.dataCache[""+parts[1]+""];
                                                    for(var i=0;i<response.data.length;i++){
                                                        $scope.dataCache[key].push(response.data[i]);
                                                    }                        
                                                }
                                                 //console.log($scope.dataCache[""+parts[1]+""]);
                                                 $timeout(function() {
                                                    $('.selectpicker').selectpicker('refresh');
                                                 });
                                                commonsTools.hideDialogLoading();
                                         },function(error){
                                             commonsTools.hideDialogLoading();
                                             commonsTools.showMessageDialog(error);
                                         });
                                  }//end if(obj.id == "load")
                              }//end if(parts.length>1)
                          }else{         
                                $scope.listDialogBuilder(model,index);
                                 var modalID = "";
                                var endIndex = index;
                                if(endIndex==1){
                                    modalID = "myModal";
                                }else if(endIndex==2){
                                     modalID = "globalModal";
                                }else if(endIndex==3){
                                     modalID = "myModal1";
                                }else if(endIndex==4){
                                     modalID = "myModal2";
                                }
                                $("#"+modalID).modal("toggle");
                                $("#"+modalID).modal("show");
                         }//end if(data.value<=$scope.pagination.pageSize)
                    },function(error){
                         commonsTools.hideDialogLoading();
                         commonsTools.showMessageDialog(error);
                    });
           
        };

       /**
        * 
        * @param {type} model
        * @param {type} entityName
        * @param {type} moduleName
        * @returns {undefined}
        */
        $scope.getData2 = function(model ,entityName,moduleName){
//            console.log("$scope.getData2 ===      "+model+" === "+entityName+" ==== "+moduleName+" === ");
            var parts = model.split(".");
            if(parts.length>1){
                $scope.dataCache[""+parts[1]+""] = new Array();                
               //var obj = array[array.length-1];              
                               
                    $scope.showDialogLoading("Chargement ...","white","#9370db","0%","0%");               
                     //Chargement des donn�es
                     urlPath = "http://"+$location.host()+":"+$location.port()+"/"+angular.lowercase(moduleName)+"/"+angular.lowercase(entityName)+"/filter/"+$scope.temporalPagination.beginIndex+"/"+$scope.temporalPagination.pageSize;
                     $http.get(urlPath).then( 
                       function(response){
                            //alert("Data: "+response.data+" === "+model);   
                            var parts = model.split(".");
                            if(parts.length>1){
//                                    $scope.dataCache[""+parts[1]+""] = new Array();
                                //var data = $scope.dataCache[""+parts[1]+""];
                                for(var i=0;i<response.data.length;i++){
                                    $scope.dataCache[""+parts[1]+""].push(response.data[i]);
                                }                        
                            }
                             //console.log($scope.dataCache[""+parts[1]+""]);
                             $timeout(function() {
                                $('.selectpicker').selectpicker('refresh');
                             });
                            commonsTools.hideDialogLoading();
                     },function(error){
                         commonsTools.hideDialogLoading();
                         commonsTools.showMessageDialog(error);
                     });
                 }   
                    
            
                

        };
       /**
         reinitialise les données temporaires
       **/
        $scope.reset = function(){
           $scope.temporalDatas = [];
           $scope.tableheaderselected= false;
           $scope.temporalData = {};
           $scope.temporalModel = {};
           $scope.currentObject = {};
           $scope.resourcesCache = {};    
//           $scope.dataCache = {};
          if($scope.selectedObjects){
             for(var i=0;i<$scope.selectedObjects.length;i++){
                $scope.selectedObjects[i].selected = false;
             }
          }
          $scope.selectedObjects = [];
        };
        
        $scope.addNewCalendar = function(){
           $scope.windowType = 'new';
           //Mise a jour des tempons
            $scope.reset();
            $scope.suffixedittitle = 'Nouveau';
           //alert($scope.currentObject.actions);          
           $scope.displayEditPanel();
           
          //console.log(viewElem.innerHTML);
           
        };

          /**
              Mise a jour 
          **/
        $scope.addElementAction = function(){
           $scope.windowType = 'new';
           //Mise a jour des tempons
            $scope.reset();
           $scope.createEmptyObject($scope.metaData);
           $scope.suffixedittitle = 'Nouveau';
           //alert($scope.currentObject.actions);          
           $scope.displayEditPanel();
           
          //console.log(viewElem.innerHTML);
           
        };

          /**
                 Enregistrement en BD

          **/
          $scope.saveOrUpdate = function(){
               //On affiche le dialog
               $scope.showDialogLoading("Chargement ...","white","#9370db","0%","0%");
               try{  
                     var validate = $scope.validateFields($scope.metaData , $scope.currentObject);
                     //console.log("$scope.saveOrUpdate === "+$scope.uniqueContraints);
                     if($scope.pagination.totalPages>0){
                         $scope.pagination.currentPage = $scope.pagination.beginIndex+1;
                     }else{
                         $scope.pagination.currentPage = 0;
                     }                 
                     if(validate.length>0){
                        var message = "";
                        for(var i=0; i<validate.length;i++){
                            message = message+"<br/>"+validate[i];
                        }
                        $scope.hideDialogLoading();
                        $scope.notifyWindow("Les champs suivants sont incorrects" ,message,"danger");                        
                     }else{
                        if($scope.windowType=='update'){
                           restService.update($scope.currentObject).$promise
                             .then(function(entity){ 
                                        //Reinitialisation du context
                                        $scope.reset();
                                        //Rechargement des données
                                        $scope.displayListPanel();
    //                                    $scope.loadData();
    //                                    $scope.hideDialogLoading();
                                        $scope.notifyWindow("Status Operation" ,"L'opération s'est déroulée avec sucess","success");
                                        if($scope.dataCache['resources']&&$scope.dataCache['resources'].length>0){
                                            for(var i=0 ; i<$scope.dataCache['resources'].length;i++){
                                                var arrayR = $scope.dataCache['resources'];
                                                var arrayN = $scope.dataCache['names'];
                                                var resource = new Array();
                                                var name = new Array();
                                                resource.push(arrayR[i]);
                                                name.push(arrayN[i]);
                                                $http.defaults.headers.common['names']= angular.toJson(name); 
                                                restService.uploadFile(resource)
                                                    .then(function(response){
//                                                        alert(angular.toJson(response))
                                                          if(i==($scope.dataCache['resources'].length-1)){
                                                            $scope.dataCache['resources'] = new Array();
                                                            $scope.dataCache['names'] = new Array();
                                                          }
                                                    },function(error){
                                                        if(i==($scope.dataCache['resources'].length-1)){
                                                            $scope.dataCache['resources'] = new Array();
                                                            $scope.dataCache['names'] = new Array();
                                                          }
                                                        $scope.notifyWindow("ERREUR" ,"Le transfert des ressources a échoué <br> Veuillez consulter les logs pour plus de détails","success");
                                                    });
                                            }//end for(var i=0 ; i<$scope.dataCache['resources'].length;i++)
                                               
                                        }//end if($scope.dataCache['resource']&&$scope.dataCache['resource'].length>0)
                          },function(error){
                              commonsTools.hideDialogLoading();
                              commonsTools.showMessageDialog(error);
                          });          
                        }else if($scope.windowType=='new'){
                            restService.uniqueProperties($scope.uniqueContraints).$promise.then(function(response){
                                //console.log("$scope.saveOrUpdate === Constraint result "+response);
                                if(response&&response.length>0){
                                    var message ="";
                                    for(var i=0; i<response.length;i++){
                                        message = message+"<br/>"+response[i].fieldLabel;
                                    }
                                    $scope.notifyWindow("Les champs suivants violent la contrainte d'unicité" ,message,"danger");
                                    commonsTools.hideDialogLoading();
                                }else{
                                    restService.save($scope.currentObject).$promise.then(function(entity){
                                        //$scope.notifyWindow("Status Operation" ,"L'opération de save s'est déroulée avec sucess","success"); 
                                        //Reinitialisation du context
                                          //Rechargement des données
                                          $http.defaults.headers.common['names']= angular.toJson($scope.dataCache['names']); 
                                          restService.count($scope.predicats)
                                               .$promise.then(function(data){
                                                    $scope.pagination.currentPage=1;
                                                   $scope.pagination.totalPages = data.value ;                                                  
                                                    //$scope.metaData = metaData;                                        
                                                    $scope.searchCriteria = new String();               
                                                    //$scope.listFramePanelBuilder(metaData);
//                                                    $scope.loadData();
                                                    $scope.reset();
                                                    $scope.displayListPanel();
                                                    $scope.notifyWindow("Status Operation" ,"L'opération s'est déroulée avec sucess","success");   
                                              }
                                              , function(error){
                                                  commonsTools.hideDialogLoading();
                                                  commonsTools.showMessageDialog(error);                                        
                                                    
                                            });
                                            if($scope.dataCache['resources']&&$scope.dataCache['resources'].length>0){
                                                for(var i=0 ; i<$scope.dataCache['resources'].length;i++){
                                                    var arrayR = $scope.dataCache['resources'];
                                                    var arrayN = $scope.dataCache['names'];
                                                    var resource = new Array();
                                                    var name = new Array();
                                                    resource.push(arrayR[i]);
                                                    name.push(arrayN[i]);
                                                    $http.defaults.headers.common['names']= angular.toJson(name); 
                                                    restService.uploadFile(resource)
                                                        .then(function(response){
    //                                                        alert(angular.toJson(response))
                                                              if(i==($scope.dataCache['resources'].length-1)){
                                                                $scope.dataCache['resources'] = new Array();
                                                                $scope.dataCache['names'] = new Array();
                                                              }
                                                        },function(error){
                                                            if(i==($scope.dataCache['resources'].length-1)){
                                                                $scope.dataCache['resources'] = new Array();
                                                                $scope.dataCache['names'] = new Array();
                                                              }
                                                            $scope.notifyWindow("ERREUR" ,"Le transfert des ressources a échoué <br> Veuillez consulter les logs pour plus de détails","success");
                                                        });
                                                }//end for(var i=0 ; i<$scope.dataCache['resources'].length;i++)
                                            }//end if($scope.dataCache['resource']&&$scope.dataCache['resource'].length>0)
                                          //$scope.displayListPanel();
                                          //$scope.hideDialogLoading();
                                           
                                    },function(error){
                                        commonsTools.hideDialogLoading();
                                        commonsTools.showMessageDialog(error);
                                    });     
                                //end of the insertion
                              }   
                            } , function(error){
                                  commonsTools.hideDialogLoading();
                                  commonsTools.showMessageDialog(error);
                            });
                                   
                        }
                         //Reinitialisation du context
                        //$scope.reset();
                        //Rechargement des données
                        //$scope.displayListPanel();                           
                     }   
                    
                }catch(ex){
                    //$scope.notifyWindow("Une erreur est servenu pendant le traitement" ,"<br/>"+ex.message,"danger");
                     $scope.hideDialogLoading();
                     //console.log(ex.message);
                }        
            
          };

//          /**
//               Update 
//          **/
          $scope.updateAction = function(){
               $scope.dataCache['resources'] = new Array();
               $scope.dataCache['names'] = new Array();
               $scope.windowType = 'update';
               if($scope.showApplication==false){
                    //Recuperation du premier element
                   if($scope.selectedObjects.length>0){
                       $scope.currentObject = $scope.selectedObjects[0];
                   }
                   $scope.selectedObjects = [];
                   //Chargement rafresh data from server
                   $scope.suffixedittitle = $scope.currentObject.designation;                   
                   //$scope.currentObject = restService.findById($scope.currentObject.code);
                   //$scope.createEmptyObject($scope.metaData);
                   $scope.displayEditPanel();
               }else{
                   //Traitement des modules
                   if($scope.currentObject.active==true){
                       //Mise a jour de l'installation
                      $scope.installApplication();
                   }else{
                       //Mise a jour des informations du repertoires addons
                        $scope.updateApplication();
                   }
               }
               //console.log("Vous avez cliquez sur  Update : "+$scope.currentObject.name+" ::::: "+$scope.currentObject.shortDescription);              
          };

          /**
           * 
           * @param {type} item
           * @returns {undefined}
           */
          $scope.viewAction = function(item){
                $scope.showDialogLoading("Chargement ...","white","#9370db","0%","0%");
                $scope.windowType = 'view';
                $scope.selectedObjects = [];               
                //Recuperation du premier element
                $scope.currentObject = item;
                var index = commonsTools.getIndex($scope.datas,item);
                if(angular.isDefined(index)){
                    $scope.pagination.currentPage = $scope.pagination.beginIndex+index+1;
                }//end if(angular.isDefined(index))
                $scope.suffixedittitle = $scope.currentObject.designation;
                 //Cas exceptionnel des application
                if($scope.showApplication==true){
                    //alert(angular.toJson($scope.currentObject));
                    if($scope.currentObject.active){
                        $scope.exportbtnlabel = "Desinstaller";
                    }else{
                        $scope.exportbtnlabel = "Installer";
                    }
                }
                restService.findById(item.id).$promise
                        .then(function(data){
                            $scope.currentObject = data;
                            $scope.displayEditPanel();
                            $scope.hideDialogLoading();
                },function(error){
                     $scope.hideDialogLoading();
                     commonsTools.showMessageDialog(error);
                } );
                //refresh data from data store
                //$scope.currentObject = restService.findById($scope.currentObject.code);         
               //$scope.createEmptyObject($scope.metaData);               
          };

          /**
           * 
           * @param {type} dasboardID
           * @returns {undefined}
           */
          $scope.showEntrypanel = function(dasboardID , entryID){
              var data = commonsTools.getDashoardEntryDataFromID($scope.currentObject,dasboardID ,entryID);
//              alert("vous avez clique sur showEntrypanel ::: "+angular.toJson(data)+" == "+dasboardID+"   "+entryID);
              if(data){
                  var container = document.createElement("div");
                  container.setAttribute("style","height: 100%; width: 100%;");
                  container.setAttribute("id",dasboardID);
                  var viewElem = commonsTools.dashboardEntryBuilder(dasboardID,data);
                   //listElem.append(angular.element(content)); 
                  var compileFn = $compile(viewElem);
                  compileFn($scope);
                  if(viewElem){
                    container.appendChild(viewElem);
                     // ///Remplacement dans la vue
                      var items = $element.find("div");
                      for(var i=0; i<items.length;i++){
                           if(items.eq(i).attr("id")==dasboardID){
                                 items.eq(i).replaceWith(container);
                                  //console.log(" ======================= on a trouve report  innerpanel");
                           }//end if(items.eq(i).attr("id")=="innerpanel")  
                      }//end if(items.eq(i).attr("id")=="innerpanel")
                  }//end if(viewElem)
              }//end if(data)
          };
          /**
           * 
           * @param {type} model
           * @param {type} entity
           * @param {type} method
           * @returns {undefined}
           */
          $scope.dashboardEntryBtn = function(model , entity , method){
              //chargement des metaData
              var url = "http://"+$location.host()+":"+$location.port()+"/"+angular.lowercase(model)+"/"+angular.lowercase(entity)+"/meta";
              commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%");
              $http.get(url)
                      .then(function(response){
                          var metadata = response.data ;
                          var url2 = "http://"+$location.host()+":"+$location.port()+"/"+angular.lowercase(model)+"/"+angular.lowercase(entity)+"/"+method;
                          $http.get(url2)
                                  .then(function(response){
                                      var data = response.data;
                                      $scope.listDialogBuilderExternal(data,metadata,1);
                                      commonsTools.hideDialogLoading();
                                      $("#globalModal").modal("toggle");
                                      $("#globalModal").modal("show");
                                  },function(error){
                                        commonsTools.hideDialogLoading();
                                        commonsTools.showMessageDialog(error);
                                  });
                      },function(error){
                          commonsTools.hideDialogLoading();
                          commonsTools.showMessageDialog(error);
                      });
          };
          /**
           * 
           */
          $scope.$on("displayitem",function(event , args){
                var index = (args.index-1)%$scope.pagination.pageSize;
                var item = $scope.datas[index];
//                console.log("displayitem ======== "+index);
                if($scope.windowType=='view'){
                     $scope.viewAction(item);
                }else if($scope.windowType=='update'){
                     $scope.viewAction(item);
                     $scope.windowType = 'update';
                }//end if($scope.windowType=='view')
                $scope.pagination.currentPage = args.index ;
          });
          /**
           * 
           * @returns {undefined}
           */
          $scope.deleteListAction = function(){
              var result = confirm("Voulez vous supprimer les "+$scope.selectedObjects.length+" selectionnés ?");
              if(result==true){
                    commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%");
                    if($scope.selectedObjects && $scope.selectedObjects.length>0){
                       restService.deleteAll($scope.selectedObjects).$promise
                              .then(function(){
                                     $scope.reset();
                                     //Rechargement des données
                                     $scope.displayListPanel();
                                     $scope.hideDialogLoading();
                                     $scope.notifyWindow("Status Operation" ,"L'opération s'est déroulée avec sucess","success");
                              },function(error){
                                  $scope.hideDialogLoading();
                                  commonsTools.showMessageDialog(error);
                              });
                      //$scope.notifyWindow("Status Operation" ,"L'opération s'est déroulée avec sucess","success");                   
                 }else{
                     $scope.notifyWindow("Suppression Impossible" ,"<br/> Veuillez selectionner au moins une ligne","warning");
                 }      
             }//end if(result==true){
          };
          /**
           * Suppression des informations
           * @returns {undefined}
           */
          $scope.deleteAction = function(){              
                    var result = confirm("Voulez vous supprimer : "+$scope.currentObject.designation+" ?");
                    if(result==true){
                            $scope.showDialogLoading("Chargement ...","white","#9370db","0%","0%");
                            try{ // console.log("$scope.deleteAction = Vous avez cliquez sur  delete == "+$scope.selectedObjects);
                                  if($scope.currentObject){
                                      restService.delete($scope.currentObject).$promise.then(function(){
                                          $scope.reset();
                                         //Rechargement des données
                                         $scope.displayListPanel();
                                         $scope.hideDialogLoading();
                                         $scope.notifyWindow("Status Operation" ,"L'opération s'est déroulée avec sucess","success");
                                      },function(error){
                                          $scope.hideDialogLoading();
                                          commonsTools.showMessageDialog(error);
                                          //console.log(angular.toJson(error));
                                      });
                                  }else{
                                      $scope.notifyWindow("Suppression Impossible" ,"<br/> Veuillez selectionner au moins une ligne","warning");
                                  }
                            }catch(ex){
                              $scope.notifyWindow("Une erreur est servenu pendant le traitement" ,"<br/>"+ex.message,"danger");
                            }   
                        }//end if(result==true)
          };

          /**
           impoort
          **/
          $scope.importAction = function(){        
                        
              console.log("Vous avez cliquez sur  Import ");
          };

          $scope.exportAction = function(){
             
                 console.log("Vous avez cliquez sur  Export ");
             
          };
          
         
          /**
           * 
           * @param {type} source
           * @returns {undefined}
           */
          $scope.printAction = function(){   
              $scope.showreporttitle = true;
            $scope.displayReportPanel();            
          };
          /**
           * Installation et desinstallation
           * @returns {undefined}
           */
          $scope.installAction = function(){
              if($scope.currentObject.active){
                  //Deja installer on va proceder a la desinstallation
                  $scope.uninstallApplication();
              }else{
                  //Non installer on va proceder a la desinstallation
                  $scope.installApplication();
              }
          };

/**
 * 
 * @param {type} model
 * @returns {undefined}
 */
         $scope.annulerAction2 = function(model){
            $scope.innerWindowType = false;            
             if(model){
                var key = commonsTools.keygenerator(model);
                var obj = {id:'load' , designation:'Charger les données ....'};
                $scope.dataCache[key] = new Array();
                $scope.dataCache[key].push(obj);
//                alert("Vous avez ferme la fenetre modal ::::: "+model+"===="+key+" === "+angular.toJson(data));
//                 if(data && angular.isArray(data)){
//                   
//                 } //end if(data && angular.isArray(data)){            
            }//end if(model){
            $timeout(function() {
                $('.selectpicker').selectpicker('refresh');

            });
         };
         /**
          * 
          * @param {type} type
          * @returns {undefined}
          */
         $scope.switchTo = function(type){
             if(type=="calendar"){
                 $scope.previousType="calendar";
                 $scope.calendarFramePanelBuilder($scope.metaData);
             }else if(type=="tree"){
                 $scope.previousType="list";
                 $scope.listFramePanelBuilder($scope.metaData);
             }
         };
          /**
            Annulation de la saisie
          **/
          $scope.annulerAction = function(){
             //console.log("Vous avez cliquez sur annulerAction");
             $scope.showDialogLoading("Chargement ...","white","#9370db","0%","0%");
             $scope.enablefollowerpanel = false;
             try{                
                 if(angular.isDefined($scope.currentObject)){
                    restService.cancel($scope.currentObject);                      
                 }
                 $scope.pagination.currentPage = $scope.pagination.beginIndex+1;
                 $scope.reset(); 
                 $scope.dataCache['resources'] = new Array();
                 $scope.dataCache['names'] = new Array();
                 
                 $scope.displayListPanel();

             }catch(ex){
                $scope.notifyWindow("Une erreur est servenu pendant le traitement..." ,"<br/>"+ex.message,"danger");
             }
            
          };

          /**
             Recherche les données selon les criteres contenu dans predicats
          **/
          $scope.searchAction = function(){
              $scope.showDialogLoading("Chargement ...","white","#9370db","0%","0%");
              try{
                      restService.filter($scope.predicats ,$scope.pagination.currentPage , $scope.pagination.pageSize)
                               .$promise.then(function(datas){
                                     //console.log('$scope.loadData = function() :::::::::::::::: '+datas);
                                    if(datas){
                                        $scope.datas = datas;
                                        $scope.listFramePanelBuilder($scope.metaData);
                                        $scope.hideDialogLoading();
                                    }
                               } ,function(error){
                                   $scope.hideDialogLoading();
                                   commonsTools.showMessageDialog(error);
                               });                       

               }catch(ex){
                   $scope.hideDialogLoading();
                    commonsTools.notifyWindow("Une erreur est servenu pendant le traitement" ,"<br/>"+ex.message,"danger");
                } 
          };

          $scope.searchCriteriaBuilder = function(){
            $scope.searchCriteria = new String();
            for(var i=0; i<$scope.predicats.length;i++){
                if($scope.predicats[i].fieldValue){
                  var oredicat = $scope.predicats[i].fieldName+" "+$scope.predicats[i].type+" "+$scope.predicats[i].fieldValue;
                   $scope.searchCriteria = $scope.searchCriteria+" "+oredicat+" ;"
                }
            }
          };

         /**
             Gestion des messages
         **/
         $scope.messagesAction = function(){
             //activer le zone de saisie des messages
             $scope.enablefollowerpanel = true;
             $scope.dataCache['messageobject'] = $scope.createemptyMessage(null,null,null);
             $scope.messageType = "outer";           
//             console.log("Vous avez cliquez sur  messagesAction ");
         };


          /**
             Gestion des notes internes
          **/
         $scope.notesInterneAction = function(){
              $scope.enablefollowerpanel = true;
              $scope.dataCache['messageobject'] = $scope.createemptyMessage(null,$rootScope.globals.user,null);
              $scope.dataCache['messageobject'].sender = $rootScope.globals.user;
              $scope.dataCache['messageobject'].reciever = $rootScope.globals.user;
              $scope.messageType = "inner";                 
//              console.log("Vous avez cliquez sur  notesInterneAction ");
         };
/**
             * 
             * @param {type} canal
             * @param {type} reciever
             * @param {type} body
             * @returns {controllers_L18.$scope.createemptyMessage.message|type.createemptyMessage.message|type@call;$asArray.createemptyMessage.message|cfg.type.createemptyMessage.message|Object|paramTypes@call;type.createemptyMessage.message}
             */
            $scope.createemptyMessage = function(canal,reciever,body){
                var message = new Object();
                message.id = -1;message.compareid=-1;message.designation=null;
                message.editTitle=null;message.listTitle=null;message.moduleName=null;
                message.selected=false;message.createonfield=true;message.desablecreate=false;
                message.desabledelete=false;message.serial=null;message.activefilelien=false;
                message.footerScript=null;message.activatefollower=false;message.date=null;
                message.status=false;message.piecesjointe=new Array();message.sender=$scope.currentUser;
                message.canal=canal;message.reciever=reciever;message.body=body;message.recievers=[];
                message.canaux=[];
                return message;
            };
         /**
           Ajout d'un abornée pour un message
         **/
         $scope.editPanelAjoutAborne = function(){            
            var url="http://"+$location.host()+":"+$location.port()+"/kerencore/utilisateur/meta";
            $http.get(url)
                    .then(function(response){
                        var metaData = response.data;
                        var url="http://"+$location.host()+":"+$location.port()+"/kerencore/canal/directe/"+$rootScope.globals.user.courriel;
                        $http.get(url)
                                .then(function(response){
                                    var datas = response.data;                                    
//                                    console.log("Vous avez cliquez sur  editPanelAjoutAborne "+datas.length);  
                                    $scope.listDialogWithDataBuilder("dataCache.follower",metaData,datas);
                                    //Appele de la fenetre modale
                                    $("#myModal").modal("toggle");
                                    $("#myModal").modal("show");
                                },function(error){
                                    commonsTools.showMessageDialog(error);
                                });
                    },function(error){
                        commonsTools.showMessageDialog(error);
                    });
            
            
         };
         /**
          * 
          * @returns {undefined}
          */
         $scope.buildfollowermenu = function(){
             var ulElem = document.createElement("ul");
             ulElem.setAttribute("class","dropdown-menu");
             ulElem.setAttribute("role","menu");
             ulElem.setAttribute("aria-labelledby","followerbtn");
             ulElem.setAttribute("id","followermenuid");
             var lielem = document.createElement("li");
             ulElem.appendChild(lielem);
             lielem.setAttribute("role","presentation");
             var aElem = document.createElement("a");
             lielem.appendChild(aElem);
             aElem.setAttribute("role","menuitem");aElem.setAttribute("tabindex","-1");
             aElem.setAttribute("href","#");aElem.setAttribute("ng-click","editPanelAjoutAborne()");
             aElem.appendChild(document.createTextNode("Ajouter des abonnées"));
             //Menu canaux
             lielem = document.createElement("li");
             ulElem.appendChild(lielem);
             lielem.setAttribute("role","presentation");
             aElem = document.createElement("a");
             lielem.appendChild(aElem);
             aElem.setAttribute("role","menuitem");aElem.setAttribute("tabindex","-1");
             aElem.setAttribute("href","#");aElem.setAttribute("ng-click","editPanelAjoutCanaux()");
             aElem.appendChild(document.createTextNode("Ajouter des canaux"));
             lielem = document.createElement("li");
             ulElem.appendChild(lielem);
             lielem.setAttribute("role","presentation");
             //Bar separateur
             lielem = document.createElement("li");
             ulElem.appendChild(lielem);
             lielem.setAttribute("class","dropdown-divider");
             //Construction des menus
             if($scope.dataCache['follower'].abonnes.length>0){
                 for(var i=0;i<$scope.dataCache['follower'].abonnes.length;i++){
                     var abonne = $scope.dataCache['follower'].abonnes[i];
                        lielem = document.createElement("li");
                        ulElem.appendChild(lielem);
                        lielem.setAttribute("role","presentation");
                        var spanElem = document.createElement("span");
                        lielem.appendChild(spanElem);
                        spanElem.setAttribute("style","display: inline-block;");
                        var span1 = document.createElement("span");
                        spanElem.appendChild(span1);
                        span1.setAttribute("style","display: inline-block;margin-right: 15px;");
                        aElem = document.createElement("a");
                        span1.appendChild(aElem);
                        aElem.setAttribute("role","menuitem");aElem.setAttribute("tabindex","-1");
                        aElem.setAttribute("href","#");aElem.setAttribute("ng-click","editPanelAjoutCanaux()");
                        aElem.appendChild(document.createTextNode(abonne.designation));
                        span1 = document.createElement("span");
                        spanElem.appendChild(span1);
                        span1.setAttribute("style","display: inline-block;");
                        aElem = document.createElement("a");
                        span1.appendChild(aElem);
                        aElem.setAttribute("role","menuitem");aElem.setAttribute("tabindex","-1");
                        aElem.setAttribute("href","#");aElem.setAttribute("ng-click","deletefollower('abonnes','"+i+"')");
                        var span2 = document.createElement("span");
                        aElem.appendChild(span2);
                        span2.setAttribute("class","glyphicon glyphicon-trash");
                        span2.setAttribute("aria-hidden","true");
                 }//end for(var i=0;i<$scope.dataCache['follower'].abonnes.length;i++)
           }//end if($scope.dataCache['follower'].abonnes.length>0)
           if($scope.dataCache['follower'].canaux.length>0){
            for(var i=0;i<$scope.dataCache['follower'].canaux.length;i++){
                   var canal = $scope.dataCache['follower'].canaux[i];
                   lielem = document.createElement("li");
                    ulElem.appendChild(lielem);
                    lielem.setAttribute("role","presentation");
                    var spanElem = document.createElement("span");
                    lielem.appendChild(spanElem);
                    spanElem.setAttribute("style","display: inline-block;");
                    var span1 = document.createElement("span");
                    spanElem.appendChild(span1);
                    span1.setAttribute("style","display: inline-block;margin-right: 15px;");
                    aElem = document.createElement("a");
                    span1.appendChild(aElem);
                    aElem.setAttribute("role","menuitem");aElem.setAttribute("tabindex","-1");
                    aElem.setAttribute("href","#");aElem.setAttribute("ng-click","editPanelAjoutCanaux()");
                    aElem.appendChild(document.createTextNode(canal.designation));
                    span1 = document.createElement("span");
                    spanElem.appendChild(span1);
                    span1.setAttribute("style","display: inline-block;");
                    aElem = document.createElement("a");
                    span1.appendChild(aElem);
                    aElem.setAttribute("role","menuitem");aElem.setAttribute("tabindex","-1");
                    aElem.setAttribute("href","#");aElem.setAttribute("ng-click","deletefollower('canaux','"+i+"')");
                    var span2 = document.createElement("span");
                    aElem.appendChild(span2);
                    span2.setAttribute("class","glyphicon glyphicon-trash");
                    span2.setAttribute("aria-hidden","true");
            }//end for(var i=0;i<$scope.dataCache['follower'].abonnes.length;i++)
           }//end if($scope.dataCache['follower'].abonnes.length>0)
           var compileFn = $compile(ulElem);
           compileFn($scope);
           //Cas des canaux
           $("#followermenuid").replaceWith(ulElem);
         };
         $scope.deletefollower = function(type , id){
             if(type=='abonnes'){
                 $scope.dataCache['follower'].abonnes.splice(id ,1);
             }else if(type=='canaux'){
                 $scope.dataCache['follower'].canaux.splice(id ,1);
             }//end if(type=='abonnes')
              var url = "http://"+$location.host()+":"+$location.port()+"/kerencore/follower/"+$scope.dataCache['follower'].id;
              $http.put(url,$scope.dataCache['follower'])
                    .then(function(response){
                         var url = "http://"+$location.host()+":"+$location.port()+"/kerencore/follower/entity/"+$scope.currentObject.serial+"/"+$scope.currentObject.id;
                        $http.get(url)
                                .then(function(response){
                                    $scope.dataCache['follower'] = response.data;  
                                    $scope.buildfollowermenu();
                                },function(error){
                                    commonsTools.showMessageDialog(error);
                                });
                    },function(error){
                        commonsTools.showMessageDialog(error);
                    });
                    
         };
          /**
           Ajout d'un abornée pour un message
         **/
         $scope.editPanelAjoutCanaux = function(){
            var url="http://"+$location.host()+":"+$location.port()+"/kerencore/canal/meta";
            $http.get(url)
                    .then(function(response){
                        var metaData = response.data;
                        var url="http://"+$location.host()+":"+$location.port()+"/kerencore/canal/canaux/"+$rootScope.globals.user.courriel;
                        $http.get(url)
                                .then(function(response){
                                    var datas = response.data;                                    
//                                    console.log("Vous avez cliquez sur  editPanelAjoutAborne "+datas.length);  
                                    $scope.listDialogWithDataBuilder("dataCache.follower",metaData,datas);
                                    //Appele de la fenetre modale
                                    $("#myModal").modal("toggle");
                                    $("#myModal").modal("show");
                                },function(error){
                                    commonsTools.showMessageDialog(error);
                                });
                    },function(error){
                        commonsTools.showMessageDialog(error);
                    });
         };
         
         /**
             Active ou desactive le suivie
         **/
         $scope.suivreAction = function(){
             $scope.activefollower = !$scope.activefollower;
             $scope.dataCache['follower'].actif = $scope.activefollower;
             if($scope.activefollower==false){
                 alert("Vous ne pourriez plus envoyer des followers");
             }//end if($scope.activefollower==false)
         };
         
        /**
         * 
         * @param {type} module
         * @param {type} model
         * @returns {undefined}
         */
         $scope.listsearchAction = function(model){
             var metaData = $scope.getCurrentMetaData(model);  
//             console.log("$scope.listsearchAction = "+angular.toJson(metaData.searchfields));
             if(metaData){
                     var part = model.split(".");
                     var predicat = new Object();
                     predicat['fieldName'] = 'designation';
                     if(metaData.searchfields&&metaData.searchfields.length>0){
                         predicat['fieldName'] = metaData.searchfields[0];
                     }//end if(metaData.searchfields&&metaData.searchfields.length>0)
                     predicat['fieldLabel'] = "null";
                     predicat['fieldValue'] = $scope.searchCriteria; 
                     predicat['type'] = 'EQUAL';
                     predicat['searchfields'] = new Array();                     
                     var predicats = new Array();
                     predicats.push(predicat);
                     $http.defaults.headers.common['predicats']= angular.toJson(predicats);
                     $scope.temporalPagination.currentPage=1;
                     $scope.temporalPagination.beginIndex=0;
                     var url = "http://"+$location.host()+":"+$location.port()+"/"+angular.lowercase(metaData.moduleName)+"/"+angular.lowercase(metaData.entityName)+"/count";
                     commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%");
                     $http.get(url)
                             .then(function(response){
                                 var result = response.data.value;
//                                 console.log("Vous avez cliquez sur ..."+model+" ==== "+$scope.searchCriteria+" === "+result);
                                 if(result<=0){
                                     $scope.dataCache[metaData.entityName] = new Array();
                                     commonsTools.hideDialogLoading();
                                 }
                                 if(result<$scope.temporalPagination.totalPages){
                                     $scope.temporalPagination.endIndex=result;
                                 }
                                 var url2 = "http://"+$location.host()+":"+$location.port()+"/"+angular.lowercase(metaData.moduleName)+"/"+angular.lowercase(metaData.entityName)+"/filter/0/"+$scope.temporalPagination.pageSize;
                                 $http.get(url2)
                                         .then(function(response){
                                             $scope.dataCache[metaData.entityName] = response.data;
                                             commonsTools.hideDialogLoading();
                                         },function(error){
                                               commonsTools.hideDialogLoading();
                                               commonsTools.showMessageDialog(error);
                                         });
                             },function(error){
                                commonsTools.hideDialogLoading();
                                commonsTools.showMessageDialog(error);
                             });
             }//end if(metaData) 
         };
         //
         $scope.followerpiecejointeviewAction = function(id,filename){
             $scope.piecejointeviewAction(id);
         },
         /**
          * 
          * @param {type} filename
          * @returns {undefined}
          */
         $scope.followerpiecejointedeleteAction = function(filename){
                commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%"); 
                var url2 = "http://"+$location.host()+":"+$location.port()+"/kerencore/resource/temporal/"+filename;
                $http({method:"DELETE" , url:url2
                }).then(function(response){
                     for(var i=0 ; i<$scope.dataCache['messageobject'].piecesjointe.length;i++){
                         var pj = $scope.dataCache['messageobject'].piecesjointe[i];
                         if(pj.filename==filename){
                             $scope.dataCache['messageobject'].piecesjointe.splice(i,1);
                         }//end if(pj.filename==filename)
                     }//end for(var i=0 ; i<$scope.messageobject.length;i++)
                     $scope.followerpiecejointeMenu($scope.dataCache['messageobject']);
                     commonsTools.hideDialogLoading();
                },function(error){
                    commonsTools.hideDialogLoading();
                    commonsTools.showDialogLoading(error);
                });          
        };
         /**
          * Suppression des piece jointes
          * @returns {undefined}
          */
         $scope.piecejointedeleteAction = function(id,filename){
             var url = "http://"+$location.host()+":"+$location.port()+"/kerencore/piecejointe/";
             commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%");              
             $http({
                 method:"DELETE", url:url+id                 
             }).then(function(response){
                 $scope.piecejointeMenu(null,$scope.currentObject,$scope.metaData);
                 var url2 = "http://"+$location.host()+":"+$location.port()+"/kerencore/resource/";
                 $http({method:"DELETE" , url:url2+filename
                 }).then(function(response){
                     
                 },function(error){
                     commonsTools.showMessageDialog(error);
                 });          
//                 commonsTools.hideDialogLoading();
             },function(error){
                 commonsTools.hideDialogLoading();
                 commonsTools.showMessageDialog(error);
             });
//             console.log("Vous avez cliquez sur piecejointedelelteAction pour la piece "+id);
         };
         /**
          * 
          * @param {type} id
          * @returns {undefined}
          */
         $scope.showpiecejointe = function(id){
             $scope.piecejointeviewAction(id);
         };
         /**
          * Evenement lie au piece jointe dans discussion
          */
         $scope.$on("showpiecejointe",function(event , args){
               if(args.pjID){
                 $scope.showpiecejointe(args.pjID); 
               }//end if(args.pjID)                      
          });
         /**
          * Consultation de la piece jointe selectionne
          * @returns {undefined}
          */
         $scope.piecejointeviewAction = function(id){
             //Recuperation de la piece jointe
             var url ="http://"+$location.host()+":"+$location.port()+"/kerencore/piecejointe/byid/id/"+id;
             commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%");   
             $http.get(url)
                     .then(function(response){
                         var pj = response.data;
                         if(pj){
                             var url = "http://"+$location.host()+":"+$location.port()+"/kerencore/resource/";
                             var parts = pj.attachename.split(".");
                             var type = "application/pdf";
                             var extension = parts[parts.length-1];
                             if(extension=='pdf'){
                                 url = url+'pdf/'+pj.filename+'/'+pj.attachename;
                             }else if(extension=='png'||extension=='jpeg'){
                                 type = "image/png";
                                 url = url+'img/'+pj.filename+'/'+pj.attachename;
                             }else if(extension=='doc'||extension=='dot'){
                                 type = "application/msword";
                                 url = url+'file/'+pj.filename+'/'+pj.attachename;
                             }else if(extension=='docx'||extension=='dotx'){
                                 type = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
                                 url = url+'file/'+pj.filename+'/'+pj.attachename;
                             }else if(extension=='xls'||extension=='xlt'||extension=='xla'){
                                 type = "application/vnd.ms-excel";
                                 url = url+'file/'+pj.filename+'/'+pj.attachename;
                             }else if(extension=='xlsx'||extension=='xltx'){
                                 type = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
                                 url = url+'file/'+pj.filename+'/'+pj.attachename;
                             }else if(extension=='ppt'||extension=='pot'||extension=='pps'||extension=='ppa'){
                                 type = "application/vnd.ms-powerpoint";
                                 url = url+'file/'+pj.filename+'/'+pj.attachename;
                             }else if(extension=='pptx'){
                                 type = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
                                 url = url+'file/'+pj.filename+'/'+pj.attachename;
                             }else if(extension=='mdb'){
                                 type = "application/vnd.ms-access";
                                 url = url+'file/'+pj.filename+'/'+pj.attachename;
                             }else if(extension=='rar'){
                                 type = "application/x-rar-compressed, application/octet-stream";
                                 url = url+'file/'+pj.filename+'/'+pj.attachename;
                             }else if(extension=='mdb'){
                                 type = "application/zip, application/octet-stream";
                                 url = url+'file/'+pj.filename+'/'+pj.attachename;
                             }else /*if(extension=='txt'||extension=='sql')*/{
                                 url = url+'text/'+pj.filename+'/'+pj.attachename;
                                 type = "text/plain";
                             }//end if(extension=='pdf')
                             $http.get(url, {responseType: "arraybuffer"})
                                     .then(function(response){
//                                         console.log("$scope.piecejointeviewAction  ============================================= "+angular.toJson(response));
//                                         var filename = response.headers['x-filename'];
//                                         var contentType = response.headers['content-type'];
                                         var linkElement = document.createElement('a');
                                         try{
                                                 var arrayBufferView = new Uint8Array(response.data );
                                                var blob = new Blob( [ arrayBufferView ], { type: type } );
                                                var urlCreator = window.URL || window.webkitURL;
                                                var docUrl = urlCreator.createObjectURL( blob );
                                                linkElement.setAttribute('href', docUrl);
                                                linkElement.setAttribute("download", pj.attachename);
                                                var clickEvent = new MouseEvent("click", {
                                                    "view": window,
                                                    "bubbles": true,
                                                    "cancelable": false
                                                });
                                                linkElement.dispatchEvent(clickEvent);
                                         } catch (ex) {
                                           commonsTools.notifyWindow("Une erreur est servenu pendant le traitement" ,"<br/>"+ex.message,"danger");
                                        }
                                         commonsTools.hideDialogLoading();
                                     },function(error){
                                         commonsTools.hideDialogLoading();
                                         commonsTools.showMessageDialog(error);
                                     });
                         }else{
                             commonsTools.hideDialogLoading();
                             commonsTools.notifyWindow("Une erreur est servenu pendant le traitement" ,"<br/>"+"Impossible de trouve la pièce jointe  : "+pj.attachename,"danger");
                         }//end if(pj)
                     },function(error){
                         commonsTools.hideDialogLoading();
                        commonsTools.showMessageDialog(error);
                     });
         };
/**
             * 
             * @returns {undefined}
             */
            $scope.sendAction = function(){
                if($scope.dataCache['messageobject'].body && $scope.dataCache['messageobject'].body.trim()!=""){
//                    var message = $scope.createemptyMessage($scope.canal,$scope.connecteduser,$scope.messagebody);
//                       $scope.dataCache['messageobject'].body = $scope.messagebody;  
                    if($scope.messageType=="outer" && $scope.activefollower==false){
                        alert("Veuillez activer le suivie des followers");
                        return;
                    }
                    if($scope.messageType=="inner"){
                        $scope.dataCache['messageobject'].sender = $rootScope.globals.user;
                        $scope.dataCache['messageobject'].reciever = $rootScope.globals.user;
                    }//end if($scope.messageType=="inner")
                    $scope.dataCache["follower"].messages.push($scope.dataCache['messageobject']);
//                    console.log("$scope.sendAction ==== "+$scope.messageType);
                    var url = "http://"+$location.host()+":"+$location.port()+"/kerencore/follower/send/"+$rootScope.globals.user.id;
                    commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%");
                    $http.post(url,$scope.dataCache["follower"])
                            .then(function(response){                               
                                     $scope.dataCache["messageobject"] = $scope.createemptyMessage(null,null,"");
                                     $scope.dataCache['follower']=response.data;
                                     $scope.buildfollowermenu();    
                                     $scope.buildFollowerMessagesView(null,$scope.dataCache['follower'].messages);
                                     $scope.followerpiecejointeMenu($scope.dataCache["messageobject"]);
                                     $scope.enablefollowerpanel = false;
                                     commonsTools.hideDialogLoading();
                            },function(error){
                                commonsTools.hideDialogLoading();
                                commonsTools.showDialogLoading(error);
                            });
                    
                }else{
                    commonsTools.notifyWindow("Erreur" ,"Le corps du message ne peut être vide","danger"); 
                }//end if($scope.messagebody && $scope.messagebody.trim()!="")
            };
            /**
             * 
             * @param {type} messages
             * @returns {undefined}
             */
            $scope.buildFollowerMessagesView = function(viewElem , messages){     
                    messages = $filter('orderBy')(messages,'-date',false);  
                    var tableElem = document.createElement('table');
                    tableElem.setAttribute('class','table table-inbox table-hover');
                    tableElem.setAttribute("id","tablefollowersid");
                    var tbody = document.createElement('tbody');
                    tableElem.appendChild(tbody);
                    if(messages){
                        for(var i=0;i<messages.length;i++){
                            var msge = messages[i];
                            var trElem = document.createElement('tr');
                            tbody.appendChild(trElem);
                            if(msge.status==false){
                                trElem.setAttribute('class', ""); 
                            };
                            var tdElem = document.createElement('td');
                            trElem.appendChild(tdElem);
                            tdElem.setAttribute('class',"view-message  dont-show");
                            tdElem.appendChild(document.createTextNode(msge.sender.intitule));
                         //Marqueur de lecture    
                            tdElem = document.createElement('td');
                            trElem.appendChild(tdElem);
                            tdElem.setAttribute('class',"view-message");
                            tdElem.appendChild(document.createTextNode(msge.body));                            
                            //piecejointe
                            tdElem = document.createElement('td');
                            trElem.appendChild(tdElem);
                            tdElem.setAttribute('class',"view-message");
                            if(msge.piecesjointe){
                                var spanElem= document.createElement("span");
                                tdElem.appendChild(spanElem);
                                spanElem.setAttribute("style","display:inline-block;")
                                for(var j=0 ; j<msge.piecesjointe.length;j++){
                                    var pj = msge.piecesjointe[j];
                                    var span = document.createElement("span");
                                    spanElem.appendChild(span);
                                    span.setAttribute("class","glyphicon glyphicon-paperclip");
                                    span.setAttribute("aria-hidden","true");
                                    var aElem = document.createElement("a");
                                    aElem.setAttribute("href","#");
                                    aElem.setAttribute("ng-click","showpiecejointe('"+pj.id+"')");
                                    aElem.appendChild(document.createTextNode(pj.attachename)),
                                    span.appendChild(aElem);
                                }//end for(var j=0 ; j<msge.piecesjointe.lenth;j++)
                            }//end if(msge.piecesjointe)
                            //Heuree
                            tdElem = document.createElement('td');
                            trElem.appendChild(tdElem);
                            tdElem.setAttribute('class',"view-message");
//                            alert(msge.date+" === "+angular.isNumber(msge.date));
                            tdElem.appendChild(document.createTextNode(commonsTools.formatDat(new Date(msge.date))));
                        }//end for(var i=0;i<$scope.messages.length;i++)
                    }//end if($scope.messages){                    
                    var compileFn = $compile(tableElem);
                    compileFn($scope);                    
                    if(viewElem==null){
                        ///Remplacement dans la vue
                        var pbjmenu = document.querySelector('#tablefollowersid');
                        if(pbjmenu!=null){
//                            console.log("Trouve !!!!!!!!!!!!!!!!");
                             pbjmenu.replaceWith(tableElem);
                        }     
                    }else{
//                        viewElem = angular.element(viewElem);
                        var items = viewElem.find("table");
                        for(var i=0; i<items.length;i++){
                            if(items.eq(i).attr("id")=="tablefollowersid"){
//                                console.log("Trouve !!!!!!!!!!!!!!!!");
                                  items.eq(i).replaceWith(tableElem);
                            }  
                        }                        
                    }//end if(viewElem==null)
            };
        /**
         * 
         * @param {type} values
         * @returns {undefined}
         */
        $scope.buttonAction = function(data,type,states,index){
            try{  //Verifier que l'object est definie
                if(angular.isString(data)){
                    data = angular.fromJson(data);
                }//end if(angular.isString(data))
                if(data){                    
                   if(type=='action'){
                      if(data.name&&data.name=="update_pwd"){
                          var action = {id:-100,name:"update_pwd" , label:"Elements de Menu",icon:"glyphicon glyphicon-th",entityName:"PwdUser",moduleName:"kerencore",modal:true,securitylevel:0,model:'kerencore'};
                          $rootScope.$broadcast("currentActionUpdate" ,{
                                   action:action , verticalMenu:$scope.enabledVerticalMenu,index:index});  
                      }else{//Cas des autres button des types actions
                          //Create differ
//                          commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%");
                          var url="http://"+$location.host()+":"+$location.port()+"/kerencore/menuaction/bystringproperty/name/"+data.name;
                          //console.log("$scope.buttonAction = "+angular.toJson(data)+" == "+type+" === "+data.name+"==== "+url); 
                          $http.get(url)
                                  .then(function(response){                    
                                      var datas = response.data;
                                      if(datas && datas.length>0){
                                          var action = datas[0];
                                           var template = $scope.templateDataBuilder(data['template']);
                                           //commonsTools.hideDialogLoading();
                                           $rootScope.$broadcast("currentActionUpdateModal" ,{
                                                action:action , verticalMenu:$scope.enabledVerticalMenu , template:template,index:index});  
                                      }else{
                                           commonsTools.notifyWindow("Une erreur est servenu pendant le traitement" ,"<br/>"+"Impossible de trouve l'action : "+data.name,"danger");
//                                           commonsTools.hideDialogLoading();
                                      }
                                      
                                  },function(error){
//                                      commonsTools.hideDialogLoading();
                                      commonsTools.showMessageDialog(error.data);
                                  });
                      }   
                   }else if(type=='object'){//Traitement des objects message en background
                       if(data.model&&data.entity&&data.method){
                           var template = $scope.templateDataBuilder(data['template']);
                           commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%");
                           var url="http://"+$location.host()+":"+$location.port()+"/"+data.model+"/"+data.entity+"/"+data.method;
                           $http.post(url,template)
                                   .then(function(response){
                                       $scope.notifyWindow("Status Operation" ,"L'opération s'est déroulée avec sucess","success");  
                                       commonsTools.hideDialogLoading();
                                   },function(error){
                                       commonsTools.hideDialogLoading();
                                       commonsTools.showMessageDialog(error.data);
                                   });
                           //alert("Vous voulez executer la methode::: "+data.method+" de l'entite :: "+data.entity+" disponible sur la resource :: "+data.model+" data template : "+angular.toJson(template));
                       }                          
                   }else if(type=='workflow'){//Traitement du workflow
                       //console.log("$scope.buttonAction = function(data,type,states) ====== "+states);
                     if(states){
                       if(data.model&&data.entity&&data.method && $scope.currentObject){
                           commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%");
                           var url="http://"+$location.host()+":"+$location.port()+"/"+data.model+"/"+data.entity+"/"+data.method;
                            $http.defaults.headers.common['states']=states;
                            $http.put(url,$scope.currentObject)
                                   .then(function(response){
                                       $scope.currentObject = response.data;
                                       $scope.displayEditPanel();
                                       $scope.notifyWindow("Status Operation" ,"L'opération s'est déroulée avec sucess","success");  
                                       commonsTools.hideDialogLoading();
                                   },function(error){
                                       commonsTools.hideDialogLoading();
                                       commonsTools.showMessageDialog(error.data);
                                   });
                       }//end if(data.model&&data.entity&&data.method)
                     }else{
                         $scope.notifyWindow("Erreur" ,"Aucun état n'est programmé","danger");
                     }
                   }
                    
                }                                  
             }catch(ex){
                 console.error(ex);
                 commonsTools.showMessageDialog(ex);
             }
        };
       /**
        * Construit un object a partir d'un template
        * name:
        * @param {type} template
        * @returns {undefined}
        */   
      $scope.templateDataBuilder = function(template){
          if(template==null||!angular.isDefined(template)){
              return null;
          }
          //Construction du template
          var data = new Object();
          for(var key in template){
              var attr = template[key];
              var part = attr.split(".");             
              if(part.length>=1&&part[0].split('@').length==1){//Il sagit des champs
                  var value = null;
                  if(part[0]=='user'){
                     if($rootScope.globals.user){  
                        value = $rootScope.globals.user;
                     }//end if($rootScope.globals.user["'"+part[1]+"'"])
                  }else if(part[0]=='object'){
                      if($scope.currentObject){
                          value = $scope.currentObject;
                      }//end if($scope.currentObject["'"+part[1]+"'"])
                  }//end if(part[0]=='user')
                  for(var i=1 ; i<part.length;i++){
                      if(value[part[i]]){
                          value = value[part[i]];
                      }else{
                          value = null;
                          break ;
                      }//end if(value["'"+part[i]+'"'])
                  }//end for(var i=1 ; i<part.length;i++)
                  data[key] = value ;                  
              }else{//traitement des champs text
                  //console.log(key+" ================== "+attr);
                  var mesages = attr.split("@");
                  var msg = "";
                  for(var i=0;i<mesages.length;i++){
                      //console.log(key+" ================== "+mesages[i]);
                      var block = mesages[i].split(".");
                      if(block.length==1){
                          if(i==0){
                              msg = mesages[i];
                          }else{
                              msg +=" @ "+mesages[i];
                          }//end if(i==0)
                      }else if(block.length>1){
                            var value = null;
                            if(block[0]=='user'){
                                if($rootScope.globals.user){  
                                   value = $rootScope.globals.user;
                                }//end if($rootScope.globals.user["'"+part[1]+"'"])
                            }else if(block[0]=='object'){
                                if($scope.currentObject){
                                    value = $scope.currentObject;
                                }//end if($scope.currentObject["'"+part[1]+"'"])
                            }//end if(part[0]=='user')
                            for(var i=1 ; i<block.length;i++){
                                if(value[block[i]]){
                                    value = value[block[i]];
                                }else{
                                    value = null;
                                    break ;
                                }//end if(value["'"+part[i]+'"'])
                            }//end for(var i=1 ; i<part.length;i++)
                            msg += " "+value ;           
                      }//end if(part.length==1)
                  }//end for(var i=0;i<mesages.length;i++)
                  data[key] = msg ; 
              }//end 
              //console.log(key+" ================== "+angular.toJson(data));
              //console.log(key+" ====== "+angular.toJson(data)+" === Current Data : "+angular.toJson($scope.currentObject)+" Current User :"+angular.toJson($rootScope.globals.user));
          }
                        
          return data ;
      };
      /**
         Algorithme de creation du tableau     
       **/
         $scope.tableListComponent = function(metaData){
            
             var tableElem = document.createElement('table');
             tableElem.setAttribute('class' , 'table table-sm table-striped table-hover');
             tableElem.setAttribute('style' , 'margin-top: -10px;');
             tableElem.setAttribute('id' , 'table');
             //Creation du table header
             var theadElem = document.createElement('thead');
             tableElem.appendChild(theadElem);
             //Creation entete
             var  rheadElem = document.createElement('tr');
             rheadElem.setAttribute('class' ,'table-header');
             theadElem.appendChild(rheadElem);
             var thElem0 = document.createElement('th');
             var inputElem0 = document.createElement('input');
             inputElem0.setAttribute('type' , 'checkbox');
             inputElem0.setAttribute('ng-model' , 'tableheaderselected');
             inputElem0.setAttribute('ng-click' , 'onCheckboxClick()');
            thElem0.appendChild(inputElem0);
            rheadElem.appendChild(thElem0);
            //Array of field name
            var fieldnames = new Array();
            var fieldtypes = new Array();
            //Traitement des champs columns
            if(metaData.columns){
                //Sort of array
                metaData.columns = $filter('orderBy')(metaData.columns,'colsequence',false);             
                for(var i=0 ; i< metaData.columns.length;i++){
                  if(angular.isDefined(metaData.columns[i].search)
                            &&(metaData.columns[i].search==true)){
                      if(metaData.columns[i].type!='array'&&metaData.columns[i].type!='image'&&metaData.columns[i].type!='textarea'&&metaData.columns[i].type!='richeditor'){  
                            var thElem = document.createElement('th');
                            thElem.innerHTML = metaData.columns[i].fieldLabel;
                            rheadElem.appendChild(thElem);
                            fieldnames.push( metaData.columns[i].fieldName);
                            if(metaData.columns[i].type=='number'){
                                fieldtypes.push(true);
                            }else{
                                fieldtypes.push(false);
                            }
                       }//end if(metaData.columns[i].type!='array'&&metaData.columns[i].type!='image'&&metaData.columns[i]
                   }//end if(angular.isDefined(metaData.columns[i].search)
                }
            }//end if(metaData.columns){
            //Traitement des groups
            if(metaData.groups){
                //Cas des columns
                for(var i=0 ; i<metaData.groups.length;i++){
                    if(metaData.groups[i]&&metaData.groups[i].columns){
                        for(var j=0 ; j< metaData.groups[i].columns.length;j++){
                            if(angular.isDefined(metaData.groups[i].columns[j].search)
                                      &&(metaData.groups[i].columns[j].search==true)){
                                 if(metaData.groups[i].columns[j].type!='array'&&metaData.groups[i].columns[j].type!='image'
                                         &&metaData.groups[i].columns[j].type.type!='textarea'&&metaData.groups[i].columns[j].type.type!='richeditor'){   
                                    var thElem = document.createElement('th');
                                    thElem.innerHTML = metaData.groups[i].columns[j].fieldLabel;
                                    rheadElem.appendChild(thElem);
                                    fieldnames.push(metaData.groups[i].columns[j].fieldName);
                                    if(metaData.groups[i].columns[j].type=='number'){
                                        fieldtypes.push(true);
                                    }else{
                                        fieldtypes.push(false);
                                    }
                                }
                            }
                        }//end For
                    }//Fin traitement des colommes
                }
            }//end if(metaData.groups){

            //Creation du corps du tableau
            var tbodyElem = document.createElement('tbody');
            tableElem.appendChild(tbodyElem);
            var rbodyElem = document.createElement('tr');
            rbodyElem.setAttribute('ng-repeat' , ' obj in datas');
            tbodyElem.appendChild(rbodyElem);
            var tdElem = document.createElement('td');
            rbodyElem.appendChild(tdElem);
            rbodyElem.setAttribute('style' , "cursor: pointer;");
            inputElem0 = document.createElement('input');
            inputElem0.setAttribute('type' , 'checkbox');
            inputElem0.setAttribute('ng-model' , 'obj.selected');
            inputElem0.setAttribute('ng-click' , 'onRowCheckboxClick(obj)');
            tdElem.appendChild(inputElem0);
            for(var i=0 ; i< metaData.columns.length;i++){
                  if(angular.isDefined(metaData.columns[i].search)
                        &&(metaData.columns[i].search==true)){
                      if(metaData.columns[i].type!='array'&&metaData.columns[i].type!='image'&&metaData.columns[i].type!='textarea'&&metaData.columns[i].type!='richeditor'){  
                        var thElem = document.createElement('td');
                        thElem.setAttribute('ng-click' , "viewAction(obj)");
                        if(metaData.columns[i].type=='object'){
                          thElem.innerHTML = "{{obj."+metaData.columns[i].fieldName+".designation}}";
                        }else{
                          thElem.innerHTML = "{{obj."+metaData.columns[i].fieldName+"}}";
                        }
                        if(metaData.columns[i].type=='number'){
                            thElem.setAttribute('class','text-right');
                        }
                        rbodyElem.appendChild(thElem);
                     }
                  }
             }//end for(var i=0 ; i< metaData.columns.length;i++){
             //Traitement des groups
            if(metaData.groups){
                //Cas des columns
                for(var i=0 ; i<metaData.groups.length;i++){
                    if(metaData.groups[i]&&metaData.groups[i].columns){
                        for(var j=0 ; j< metaData.groups[i].columns.length;j++){
                            if(angular.isDefined(metaData.groups[i].columns[j].search)
                                      &&(metaData.groups[i].columns[j].search==true)){
                                  if(metaData.groups[i].columns[j].type!='array'&&metaData.groups[i].columns[j].type!='image'
                                          &&metaData.groups[i].columns[j].type!='textarea'&&metaData.groups[i].columns[j].type!='richeditor'){   
                                    var thElem = document.createElement('td');
                                    thElem.setAttribute('ng-click' , "viewAction(obj)");
                                    if(metaData.groups[i].columns[j].type=='object'){
                                      thElem.innerHTML = "{{obj."+metaData.groups[i].columns[j].fieldName+".designation}}";
                                    }else{
                                      thElem.innerHTML = "{{obj."+metaData.groups[i].columns[j].fieldName+"}}";                                      
                                    }
                                    if(metaData.groups[i].columns[j].type=='number'){
                                        thElem.setAttribute('class','text-right');
                                    }
                                    rbodyElem.appendChild(thElem);
                                }
                            }
                        }//end For
                    }//Fin traitement des colommes
                }
            }//end if(metaData.groups){
            //Construction du footer du tableau
            var footerElem = document.createElement('tfoot');
            footerElem.setAttribute('style','background-color: #eeeeee;');
            var trElem = document.createElement('tr');
            var thElem = document.createElement('th');
            trElem.appendChild(thElem);
            footerElem.appendChild(trElem);
            var numeric_fields = 0 ;
            for(var i=0;i<fieldnames.length;i++){
                var total = null;
                if(fieldtypes[i]==true){
                    total = commonsTools.sumTableField(fieldnames[i],$scope.datas);
//                    console.log("$scope.tableListComponent === begin  "+i+" ****** "+fieldnames[i]+"=== "+total);
                }
                thElem = document.createElement('th');
                trElem.appendChild(thElem);      
                if(total){                    
                    numeric_fields++ ;
                    thElem.appendChild(document.createTextNode(total));
                    thElem.setAttribute('class','text-right');
                }//end if(total)
            }//end for(var i=0;i<fieldnames.length;i++){
            if(numeric_fields>0){
                tableElem.appendChild(footerElem);
            }//end if(numeric_fields>0)
             var divElem = document.createElement('div');            
            return tableElem;
         };
         
        /**
         * Algorithme de creation du tableau    
         * @param {type} metaData
         * @returns {Element}
         */
         $scope.tableListComponentReport = function(metaData){
            var divresponsive = document.createElement("div");
            divresponsive.setAttribute("class","table-responsive");
             var tableElem = document.createElement('table');
//             tableElem.setAttribute('style','border:solid 1px;')
             divresponsive.appendChild(tableElem);     
             var columns = new Array();
             tableElem.setAttribute('class' , 'table  table-condensed');
             tableElem.setAttribute('style' , 'width: 100%;');
             tableElem.setAttribute('id' , 'table');
             //Creation du table header
             var theadElem = document.createElement('thead');
//             theadElem.setAttribute('style','display: table-header-group;vertical-align: middle; border-color: inherit;');
             tableElem.appendChild(theadElem);
             //Creation entete
             var  rheadElem = document.createElement('tr');
//             rheadElem.setAttribute('class' , 'small');
             theadElem.appendChild(rheadElem);
//             rheadElem.setAttribute('style','border:solid 5px;position: center;');
            //Traitement des champs columns
            if(metaData.columns){
                //Sort of array
                metaData.columns = $filter('orderBy')(metaData.columns,'colsequence',false);  
                var k =0;
                for(var i=0 ; i< metaData.columns.length;i++){
                  if(angular.isDefined(metaData.columns[i].search)
                            &&(metaData.columns[i].search==true)){
                      if(metaData.columns[i].type!='array'&&metaData.columns[i].type!='image'&&metaData.columns[i].type!='textarea'&&metaData.columns[i].type!='richeditor'){  
                            var thElem = document.createElement('th');
                            thElem.setAttribute("style","font-size: small;");
                            thElem.setAttribute("class","text-center small");
                            thElem.appendChild(document.createTextNode(metaData.columns[i].fieldLabel))  ;
                            columns[k]=metaData.columns[i].fieldName;
                            k++;
                            rheadElem.appendChild(thElem);
                       }//end if(metaData.columns[i].type!='array'
                     }//end if(angular.isDefined(metaData.columns[i].search)
                }
            }
            //Traitement des groups
            if(metaData.groups){
                //Cas des columns
                for(var i=0 ; i<metaData.groups.length;i++){
                    if(metaData.groups[i]&&metaData.groups[i].columns){
                        for(var j=0 ; j< metaData.groups[i].columns.length;j++){
                            if(angular.isDefined(metaData.groups[i].columns[j].search)
                                      &&(metaData.groups[i].columns[j].search==true)){
                                 if(metaData.groups[i].columns[j].type!='array'&&metaData.groups[i].columns[j].type!='image'
                                         &&metaData.groups[i].columns[j].type.type!='textarea'&&metaData.groups[i].columns[j].type.type!='richeditor'){   
                                    var thElem = document.createElement('th');
                                    thElem.setAttribute("style","font-size: small;");
                                    thElem.setAttribute("class","text-center small");
                                    thElem.appendChild(document.createTextNode(metaData.groups[i].columns[j].fieldLabel));
                                    columns[k]=metaData.groups[i].columns[j].fieldName;
                                     k++;
                                    rheadElem.appendChild(thElem);
                                }
                            }
                        }//end For
                    }//Fin traitement des colommes
                }
            }

            //Creation du corps du tableau
            var tbodyElem = document.createElement('tbody');
            tableElem.appendChild(tbodyElem);            
 
            for(var i=0 ; i< $scope.selectedObjects.length;i++){
                    var data = $scope.selectedObjects[i];
                    var rbodyElem = document.createElement('tr');   
                    //rbodyElem.setAttribute('class','small');
                    tbodyElem.appendChild(rbodyElem);
                    for(var j=0 ; j<columns.length;j++){ 
                        /*if(data[columns[j]])*/{   
                             var tdElem = document.createElement('td');
                            rbodyElem.appendChild(tdElem);
                            tdElem.setAttribute("style","font-size: small;");
                            tdElem.setAttribute("class","text-center small");
                            var col = data[columns[j]];
                            //console.log(columns[j]+"=============="+angular.toJson(col));                        
                            if(angular.isObject(col)){
                                tdElem.appendChild(document.createTextNode(col['designation']));
                            }else if(col){
                                 tdElem.appendChild(document.createTextNode(col));
//                                tdElem.innerHTML = col;
                                 if(angular.isNumber(col)){
                                     tdElem.setAttribute("class","text-right small");
                                 }//end if(angular.isNumber(col))
                            }else{
                                tdElem.appendChild(document.createTextNode(""));
                            }//end if(col['designation'])
                            //var tdElem = document.createElement('td');
                            //rbodyElem.appendChild(tdElem);
                        }
                       
                    }
                    
             }//construction du tableau        

            var divElem = document.createElement('div');
            divElem.appendChild(divresponsive);
//            console.log(divElem.innerHTML);
            return divresponsive;
         };
      
         /**
             Fonction de construction du filtre de recherche
         **/
         $scope.filterOptionsComponent = function(metaData){
               if($scope.currentAction&&angular.isString($scope.currentAction)){
                   $scope.currentAction = angular.fromJson($scope.currentAction);
               }
               $scope.desablecreate = metaData.desablecreate || !$scope.canCreate();
               $scope.desableupdate = !$scope.canUpdate();
               $scope.desabledelete = !$scope.canDelete();
               $scope.desableprint = !$scope.canPrint();
//               console.log("$scope.filterOptionsComponent  desablecreate = "+angular.toJson($scope.metaData));
               //Initialisation de la lisif(!$scope.predicats){
               var ulElem = document.createElement('ul');
               ulElem.setAttribute('class','dropdown-menu');
               ulElem.setAttribute('role' , 'menu');
               ulElem.setAttribute('aria-labelledby' , 'filterbtn');
               ulElem.setAttribute('id' , 'filterActionsId');
               ulElem.setAttribute('ng-repeat' , ' obj in metaData');
               
                var j = 0 ;
               for(var i=0 ; i< metaData.columns.length;i++){
                 if(angular.isDefined(metaData.columns[i].search)
                        &&(metaData.columns[i].search==true)){
                  
                     var predicat = new Object();
                     predicat['fieldName'] = metaData.columns[i].fieldName;
                     predicat['fieldLabel'] = metaData.columns[i].fieldLabel;
                     predicat['value'] = angular.isDefined($scope.predicats[j]) ? $scope.predicats[j].value : null; 
                     predicat['type'] = 'EQUAL';
                     predicat['searchfields'] = metaData.columns[i].searchfields;
                     $scope.predicats[j] = predicat;
                     //if($scope.predicats)
                     var liElem = document.createElement('li');
                     liElem.setAttribute('role' , 'presentation');
                     ulElem.appendChild(liElem);
                     //  alert("TATATATATATA === "+metaData.columns[i].type);        
                    if(metaData.columns[i].type!='array'){
                        if(metaData.columns[i].type!='image'){ 
                             //creation d'un predicat
                              var aElem = document.createElement('span');
                             //aElem.setAttribute('style' , 'border:');
                             //aElem.setAttribute()
                             aElem.setAttribute('role' , 'menuitem');
                             aElem.setAttribute('tabindex' , '-1');
                             aElem.setAttribute('href' , '#');
                             liElem.appendChild(aElem);
                             var spanElem = document.createElement('span');
                             aElem.appendChild(spanElem);
                             var inputElem = document.createElement('input');
                             spanElem.appendChild(inputElem);
                             //inputElem.setAttribute('ng-model' , 'obj.value');
                             inputElem.setAttribute('style' , 'border:none;');
                             if(metaData.columns[i].type=='string'){
                                inputElem.setAttribute('type' , 'text');
                             }else if(metaData.columns[i].type=='number'){
                               inputElem.setAttribute('type' , 'number');
                             }else if(metaData.columns[i].type=='date'){
                               inputElem.setAttribute('type' , 'date');
                             }else if(metaData.columns[i].type=='tel'){
                               inputElem.setAttribute('type' , 'tel');
                             }else if(metaData.columns[i].type=='email'){
                               inputElem.setAttribute('type' , 'email');
                             }else if(metaData.columns[i].type=='url'){
                               inputElem.setAttribute('type' , 'url');
                             }else if(metaData.columns[i].type=='time'){
                               inputElem.setAttribute('type' , 'time');
                             }else if(metaData.columns[i].type=='datetime'){
                               inputElem.setAttribute('type' , 'datetime');
                             }else if(metaData.columns[i].type=='boolean'){
                               var labelElem = document.createElement('label');
                               labelElem.setAttribute('for' , metaData.columns[i].fieldName);
                               labelElem.appendChild(document.createTextNode(metaData.columns[i].fieldLabel)); 
                               spanElem.appendChild(labelElem);
                               inputElem.setAttribute('type' , 'checkbox');
                             }else if(metaData.columns[i].type!='object'){
                                 inputElem.setAttribute('type' , 'text');                                 
                             }

                            inputElem.setAttribute('ng-model' , 'predicats['+j+'].fieldValue');
                            inputElem.setAttribute('ng-change' , 'searchCriteriaBuilder()');
                            inputElem.setAttribute('placeholder' , metaData.columns[i].fieldLabel);
                         }
                             j++;
                      
                   }else if(metaData.columns[i].type=='object'){       
                      
                   }else if(metaData.columns[i].type=='array'){
                        //alert("HOLALALALA");
                   }
               }
            } 
               return ulElem;               
         };

/**
 * 
 * @returns {undefined}
 */
         $scope.viewModeBuilder = function(){
             var divelem = document.createElement("div");
             divelem.setAttribute("id","viewmodeid");
             if($scope.viewmode){
                 var part = $scope.viewmode.split(",");
                 if(part.length>2){
                     divelem.setAttribute("class","btn-group");
                     divelem.setAttribute("role","group");
                     divelem.setAttribute("aria-label","group3");
                     for(var i=0;i<part.length;i++){
                         var buttonElem = document.createElement("button");
                         buttonElem.setAttribute("type","button");
                         buttonElem.setAttribute("class","btn btn-default btn-sm");
                         if(part[i]=="calendar"){
                             var spanElem = document.createElement("span");
                             buttonElem.appendChild(spanElem);
                             buttonElem.setAttribute("ng-click","switchTo('calendar')");
                             spanElem.setAttribute("class","glyphicon glyphicon-calendar");
                             spanElem.setAttribute("aria-hidden","true");
                             divelem.appendChild(buttonElem);
                         }else if(part[i]=="tree"){
                             var spanElem = document.createElement("span");
                             buttonElem.appendChild(spanElem);
                             buttonElem.setAttribute("ng-click","switchTo('tree')");
                             spanElem.setAttribute("class","glyphicon glyphicon-list");
                             spanElem.setAttribute("aria-hidden","true");
                             divelem.appendChild(buttonElem);
                         }//end if(part[i]=="calendar")
                     }//end for(var i=0;i<part.length;i++)
                     
                 }//end if(part.length>1)
             }//end if($scope.viewmode)
             return divelem;
         };
         /**
          * Building table list panel
          * @param {type} metaData
          * @returns {undefined}
          */
          $scope.listPanelComponent = function(metaData){               
               $scope.windowType = "list";
                var listElem  = null ; 
                var content = $scope.viewSelector('list') ;
                listElem = angular.element(content);
                if(metaData==null||!angular.isDefined(metaData)){
                   return ;
                 }
                 listElem = $scope.buildActionsMenu(listElem,null,0);
                 listElem = $scope.buildPrintActionsMenu(listElem);
                //Insertion de la zone de filter
                var items = listElem.find("ul");
                for(var i=0; i<items.length;i++){
                     
                     if(items.eq(i).attr("id")=="filterActionsId"){
                           items.eq(i).replaceWith($scope.filterOptionsComponent(metaData));
                     }  
                }
                //Insertion viewMode
                var items = listElem.find("div");
                for(var i=0; i<items.length;i++){
                     
                     if(items.eq(i).attr("id")=="viewmodeid"){
                           items.eq(i).replaceWith($scope.viewModeBuilder());
                     }  
                }
                var divElem = document.createElement("div");
                divElem.setAttribute("class","panel-body container-body-panel");
                divElem.setAttribute("id","datatable");
                divElem.setAttribute("style","height: 82%;overflow: auto;margin-top: -8%;");
                divElem.appendChild($scope.tableListComponent(metaData));
                //Insertion du tableau
                var items = listElem.find("div");
                for(var i=0; i<items.length;i++){
                     
                     if(items.eq(i).attr("id")=="datatable"){
                           items.eq(i).replaceWith(divElem);
                     }  
                }

                return listElem;
          };
          
           $scope.calendarPanelComponent = function(metaData){               
               $scope.windowType = "calendar";
               var listElem  = null ; 
                var content = $scope.viewSelector("calendar") ;
                listElem = angular.element(content);
                if(metaData==null||!angular.isDefined(metaData)){
                   return ;
                 }
//                 listElem = $scope.buildActionsMenu(listElem);
                 listElem = $scope.buildPrintActionsMenu(listElem);
                //Insertion de la zone de filter
                var items = listElem.find("ul");
                for(var i=0; i<items.length;i++){
                     
                     if(items.eq(i).attr("id")=="filterActionsId"){
                           items.eq(i).replaceWith($scope.filterOptionsComponent(metaData));
                     }  
                }
                //Insertion viewMode
                var items = listElem.find("div");
                for(var i=0; i<items.length;i++){
                     
                     if(items.eq(i).attr("id")=="viewmodeid"){
                           items.eq(i).replaceWith($scope.viewModeBuilder());
                     }  
                }
//                var divElem = document.createElement("div");
//                divElem.setAttribute("class","panel-body container-body-panel");
//                divElem.setAttribute("id","datatable");
//                divElem.setAttribute("style","height: 82%;overflow: auto;margin-top: -8%;");
//                divElem.appendChild($scope.calendarPanelComponent(metaData));
//                //Insertion du tableau
//                var items = listElem.find("div");
//                for(var i=0; i<items.length;i++){
//                     
//                     if(items.eq(i).attr("id")=="datatable"){
//                           items.eq(i).replaceWith(divElem);
//                     }  
//                }

                return listElem;
          };
     
          /**
             Fonction de construction des 
          **/
          $scope.listFramePanelBuilder = function(metaData,elem){
                  var listElem  = null ;         
                   $scope.previousType="list";
//                   console.log("listFramePanelBuilder inside ====== "+$scope.enabledVerticalMenu+" ==== "+angular.toJson(metaData));             
                  if($scope.enabledVerticalMenu){       
                      listElem = angular.element("<div>")
                                    .addClass("col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2")
                                    .attr("id" , "container")
                                    .attr("style" ,"position: absolute;width: 84.2%;height: 91%;margin-left:15.55% ;margin-right:0px;padding:0px;");
                  }else{
                    listElem = angular.element("<div>")
                                     .addClass("col-sm-12  col-md-12")
                                     .attr("id" , "container")
                                     .attr("style" ,"position: absolute;width: 100%;height: 91.0%; margin:0px;padding:0px;" );                   
                  
                  }
                 if(metaData){
                     listElem.append($scope.listPanelComponent(metaData));
                 }else{
                      if(elem){
                         listElem.append(elem);
                      }
                 }//endif(metaData)                 
                  //var content = $scope.viewSelector('list') ;
                  //listElem.append(angular.element(content)); 
                  var compileFn = $compile(listElem);
                  compileFn($scope);
                  //Insertion du tableau
                  var items = $element.find("div");
                  for(var i=0; i<items.length;i++){
                       
                       if(items.eq(i).attr("id")=="container"){
                             items.eq(i).replaceWith(listElem);
                            // console.log("listFramePanelBuilder ====== "+$scope.enabledVerticalMenu);
                       }  
                  }

                 return listElem;
          };
          
        /**
         * 
         * @param {type} metaData
         * @param {type} elem
         * @returns {unresolved}
         */  
        $scope.calendarFramePanelBuilder = function(metaData,elem){
                  $scope.previousType="calendar";
                  var listElem  = null ;                  
//                   console.log("listFramePanelBuilder inside ====== "+$scope.enabledVerticalMenu+" ==== "+angular.toJson(metaData));             
                  if($scope.enabledVerticalMenu){       
                      listElem = angular.element("<div>")
                                    .addClass("col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2")
                                    .attr("id" , "container")
                                    .attr("style" ,"position: absolute;width: 84.2%;height: 91%;margin-left:15.55% ;margin-right:0px;padding:0px;");
                  }else{
                    listElem = angular.element("<div>")
                                     .addClass("col-sm-12  col-md-12")
                                     .attr("id" , "container")
                                     .attr("style" ,"position: absolute;width: 100%;height: 91.0%; margin:0px;padding:0px;" );                   
                  
                  }
                 if(metaData){
                     listElem.append($scope.calendarPanelComponent(metaData));
                 }else{
                      if(elem){
                         listElem.append(elem);
                      }
                 }//endif(metaData)                 
                  //var content = $scope.viewSelector('list') ;
                  //listElem.append(angular.element(content)); 
                  var compileFn = $compile(listElem);
                  compileFn($scope);
                  //Insertion du tableau
                  var items = $element.find("div");
                  for(var i=0; i<items.length;i++){
                       
                       if(items.eq(i).attr("id")=="container"){
                             items.eq(i).replaceWith(listElem);
                            // console.log("listFramePanelBuilder ====== "+$scope.enabledVerticalMenu);
                       }  
                  }

                 return listElem;
          };

          /**
             Fonction de construction des 
          **/
          $scope.listFrameBuilder = function(metaData){
                  var listElem  = null ;
                  if(($scope.enabledVerticalMenu==true)){
                      listElem = angular.element("<div>")
                                    .addClass("col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2")
                                    .attr("id" , "container")
                                    .attr("style" ,"position: absolute;width: 84.2%;height: 91%;margin-left:15.55% ;margin-right:0px;padding:0px; border:solid 1px red ;");
                  }else{
                    listElem = angular.element("<div>")
                                     .addClass("col-sm-12  col-md-12")
                                     .attr("id" , "container")
                                     .attr("style" ,"position: absolute;width: 100%;height: 91.0%; margin:0px;padding:0px;" );          
                        //console.log("listFrameBuilder ====== "+$scope.enabledVerticalMenu);
                  
                  }
                  
                  listElem.append($scope.listPanelComponent(metaData));                  
                  var compileFn = $compile(listElem);
                  compileFn($scope);
                  $element.append(listElem);               
                 return listElem;
          };

           
           $scope.$on("dataLoad" , function(event ,args){
//                console.log('$scope.$on("dataLoad" , function(event ,args) :::::::::::::::: '+$scope.currentAction.viewMode+" === "+$scope.windowType);
                if($scope.windowType=="calendar"){
                     $scope.calendarFramePanelBuilder($scope.metaData);
                }else{
                    $scope.listFramePanelBuilder($scope.metaData);
                } //end if($scope.windowType=="calendar")   
               $scope.hideDialogLoading();
               $scope.pagination.hasnext();
               $scope.pagination.hasprevious();
           });
           
//         
          /**
            Reception du signal de changement de module
          **/
          $scope.$on("currentModule" , function(event , args){
                  $scope.currentModule = args.module;
                  if($scope.currentModule.hasmenu==true){
                        $scope.enabledVerticalMenu = args.verticalMenu;
                        $scope.exportbtnlabel = 'Exporter';    
                        $scope.updatebtnlabel ='Modifier';
                        $scope.deletebtnlabel='Supprimer';
                        $scope.showApplication = false;
                        if($scope.currentModule.name=='application'){                            
                            $scope.exportbtnlabel = 'Installer/Desinstaller';
                            $scope.updatebtnlabel ='Mise à jour';
                            $scope.deletebtnlabel='Supprimer';
                            $scope.showApplication = true;
                        }  
                }else if($scope.currentModule.hasmenu==false){
                    alert("Vous avez cliquez sur un module sans menu ....");
                }
                  
          });
       
       
         $scope.updateApplication = function(){
              //Create differ
                    $scope.showDialogLoading("Chargement ...","white","#9370db","0%","0%");
                    //Initialisation de l'url
                    restService.url(angular.lowercase($scope.currentAction.entityName),angular.lowercase($scope.currentAction.moduleName));
                    $scope.metaData = restService.getMetaData(null).$promise
                                    .then(function(metaData){
                                                //console.log("Chargement MetaData "+angular.toJson(metaData));
                                                $scope.predicats = new Array();
                                                $scope.metaData = metaData; 
                                                var url ="http://"+$location.host()+":"+$location.port()+"/kerencore/menumodule/refresh";
                                                //Parametres pagination
                                                $http.post(url)
                                                        .then(function(response){
//                                                             //console.log("Chargement MetaData "+angular.toJson(metaData));
                                                                $scope.predicats = new Array();
                                                                //Parametres pagination
                                                                restService.count($scope.predicats)
                                                                      .$promise.then(function(data){
                                                                          $scope.pagination.currentPage=1;
                                                                           $scope.pagination.totalPages = data.value ;                                                  
                                                                           $scope.metaData = metaData;                                        
                                                                            $scope.searchCriteria = new String();               
                                                                            $scope.listFramePanelBuilder(metaData);
                                                                            $scope.loadData();
                                                                      }
                                                                      , function(error){
                                                                          commonsTools.hideDialogLoading();
                                                                          commonsTools.showMessageDialog(error);
                                                                      });  
                                                        },function(error){
                                                            commonsTools.hideDialogLoading();
                                                            commonsTools.showMessageDialog(error);
                                                        });
                                        
                                        },function(error){
                                                commonsTools.hideDialogLoading();
                                                commonsTools.showMessageDialog(error);
                                        });
         };
          /**
           * Installation d'un nouveau module
           * @returns {undefined}
           */
          $scope.installApplication = function(){
               commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%");
                    //Initialisation de l'url
                    restService.url(angular.lowercase($scope.currentAction.entityName),angular.lowercase($scope.currentAction.moduleName));
                    $scope.metaData = restService.getMetaData(null).$promise
                                    .then(function(metaData){
                                                //console.log("Chargement MetaData "+angular.toJson(metaData));
                                                $scope.predicats = new Array();
                                                $scope.metaData = metaData; 
                                                var url ="http://"+$location.host()+":"+$location.port()+"/kerencore/menumodule/install";
                                                //Parametres pagination
                                                $http.post(url,$scope.currentObject)
                                                        .then(function(response){
//                                                             //console.log("Chargement MetaData "+angular.toJson(metaData));
                                                                $scope.predicats = new Array();
                                                                //Parametres pagination
                                                                restService.count($scope.predicats)
                                                                      .$promise.then(function(data){
                                                                          $scope.pagination.currentPage=1;
                                                                           $scope.pagination.totalPages = data.value ;                                                  
                                                                           $scope.metaData = metaData;                                        
                                                                            $scope.searchCriteria = new String();               
                                                                            $scope.listFramePanelBuilder(metaData);
                                                                            $scope.loadData();
                                                                            $scope.notifyWindow("Status Operation" ,"L'opération s'est déroulée avec sucess","success"); 
                                                                            location.reload();
                                                                      }
                                                                      , function(error){
                                                                          commonsTools.hideDialogLoading();
                                                                          commonsTools.showMessageDialog(error);
                                                                      });  
                                                        },function(error){
                                                            commonsTools.hideDialogLoading();
                                                            commonsTools.showMessageDialog(error);
                                                        });
                                        
                                        },function(error){
                                                commonsTools.hideDialogLoading();
                                                commonsTools.showMessageDialog(error);
                                        });
                
          };
          /**
           * Installation d'un nouveau module
           * @returns {undefined}
           */
          $scope.uninstallApplication = function(){
               commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%");
                    //Initialisation de l'url
                    restService.url(angular.lowercase($scope.currentAction.entityName),angular.lowercase($scope.currentAction.moduleName));
                    $scope.metaData = restService.getMetaData(null).$promise
                                    .then(function(metaData){
                                                //console.log("Chargement MetaData "+angular.toJson(metaData));
                                                $scope.predicats = new Array();
                                                $scope.metaData = metaData; 
                                                var url ="http://"+$location.host()+":"+$location.port()+"/kerencore/menumodule/uninstall";
                                                //Parametres pagination
                                                $http.post(url,$scope.currentObject)
                                                        .then(function(response){
//                                                             //console.log("Chargement MetaData "+angular.toJson(metaData));
                                                                $scope.predicats = new Array();
                                                                //Parametres pagination
                                                                restService.count($scope.predicats)
                                                                      .$promise.then(function(data){
                                                                          $scope.pagination.currentPage=1;
                                                                           $scope.pagination.totalPages = data.value ;                                                  
                                                                           $scope.metaData = metaData;                                        
                                                                            $scope.searchCriteria = new String();               
                                                                            $scope.listFramePanelBuilder(metaData);
                                                                            $scope.loadData();
                                                                            $scope.notifyWindow("Status Operation" ,"L'opération s'est déroulée avec sucess","success");  
                                                                            location.reload();
                                                                      }
                                                                      , function(error){
                                                                          commonsTools.hideDialogLoading();
                                                                          commonsTools.showMessageDialog(error);
                                                                      });  
                                                        },function(error){
                                                            commonsTools.hideDialogLoading();
                                                            commonsTools.showMessageDialog(error);
                                                        });
                                        
                                        },function(error){
                                                commonsTools.hideDialogLoading();
                                                commonsTools.showMessageDialog(error);
                                        });
                
          };
          /**
           * 
           * @param {type} template
           * @param {type} index
           * @returns {undefined}
           */
         $scope.initAction = function(template,index){
               
                if($scope.currentAction.name=="application_update"){
                     $scope.updateApplication();
                }else{
                    //Create differ
                    commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%");
                    //Initialisation de l'url
                    restService.url(angular.lowercase($scope.currentAction.entityName),angular.lowercase($scope.currentAction.model));
                    //Chargement des metaData
                    var mode = null ;
                    if($scope.currentAction.viewMode){
                        mode = $scope.currentAction.viewMode.split(",");
                    }//end if($scope.currentAction.viewMode)     
//                    console.log("InitAction ========== "+$scope.currentAction.viewMode+" ==== "+angular.toJson(mode));                                                    
                    if(mode && mode.length>0 && mode[0]=='dashboard'){
//                        alert("Chargement tableau de bord ....");
                          $scope.hideannuler=true;
                          var model = angular.lowercase($scope.currentAction.model);
                          var entity = angular.lowercase($scope.currentAction.entityName);
                          var method = angular.lowercase($scope.currentAction.method);
                          var templateID =$scope.currentAction.dashboard.id;
                          var url = "http://"+$location.host()+":"+$location.port()+"/"+model+"/"+entity+"/"+method+"/"+templateID;
                          $http.get(url)
                                  .then(function(response){
                                        $scope.currentObject = response.data;
                                        $scope.listFramePanelBuilder(null,$scope.displayDashboardPanel());                                        
                                        commonsTools.hideDialogLoading();
                                    },function(error){
                                         commonsTools.hideDialogLoading();
                                         commonsTools.showMessageDialog(error);
                                    });
                                    
                                    return ;
                    }//end if(mode.length>0 && mode[0]=='dashboard')
                    $scope.hideannuler=false;
                    if(!$scope.currentAction.modal){                            
                            restService.getMetaData($scope.currentAction).$promise
                                    .then(function(metaData){
//                                        console.log("Chargement MetaData "+angular.toJson(metaData));
                                        $scope.predicats = new Array();
                                        //Parametres pagination
                                        restService.count($scope.predicats)
                                              .$promise.then(function(data){
                                                  $scope.pagination.currentPage=1;
                                                  $scope.pagination.beginIndex = 0;
//                                                   console.log("Chargement MetaData "+data+" === "+angular.isNumber(commonsTools.objectToNumber(angular.fromJson(angular.toJson(data)))));
                                                   $scope.pagination.totalPages = data.value ;                                                  
                                                   $scope.metaData = metaData ;//commonsTools.xmlViewParser($scope.currentAct) metaData;                                        
                                                    $scope.searchCriteria = new String();               
//                                                    var viewType ="tree";
//                                                    if($scope.currentAction.viewMode){
//                                                        var part=$scope.currentAction.viewMode.split(",");
//                                                        viewType = part[0];
//                                                    }//end if($scope.currentAction.viewMode)
                                                    if(mode && mode[0]=="calendar"){
                                                       $scope.calendarFramePanelBuilder(metaData);
                                                    }else{
                                                        $scope.listFramePanelBuilder(metaData);
                                                    }//end if(viewType=="tree")
                                                    $scope.loadData();
                                              }
                                              , function(error){
                                                  commonsTools.hideDialogLoading();
                                                  commonsTools.showMessageDialog(error);
                                              });
                                        
                                    },function(error){
                                        commonsTools.hideDialogLoading();
                                        commonsTools.showMessageDialog(error);
                                    });                            
                        }else if($scope.currentAction.modal){                           
                               restService.getMetaData($scope.currentAction).$promise
                                    .then(function(metaData){  
                                        if(!$scope.metaData){
                                             $scope.metaData = metaData;
                                        }//end if($scope.metaData)
//                                        console.log("Chargement MetaData   = "+angular.toJson(metaData));
                                        if($scope.currentAction.name=='update_pwd'){
                                            var pwduser = {cle:$scope.dataCache['currentObject'].id,user:$scope.dataCache['currentObject'].courriel,password:$scope.dataCache['currentObject'].password};
                                            $scope.temporalData = pwduser;
//                                            console.log("Chargement MetaData apres $scope.createEmptyTemporalObject(metaData)  = "+angular.toJson($scope.currentObject));                               
                                        }else if($scope.currentAction.name=="modifpassword"){
//                                            console.log("Chargement MetaData   = "+angular.toJson($rootScope.globals.user));
                                            var pwduser2 = {cle:$rootScope.globals.user.id,oldpassword:$rootScope.globals.user.oldpassword,newpassword:$rootScope.globals.user.newpassword};
                                            $scope.temporalData = pwduser2;                                                                           
                                        }else{
                                            $scope.temporalData = new Object();
                                            $scope.createEmptyTemporalObject(metaData,template);                                        
                                        }
                                        if(index==null||!angular.isDefined(index)){
                                            index = 1;
                                        }else{
                                            index = new Number(index)+1;
                                        }
//                                        console.log("initAction ========== "+index);
                                        $scope.editDialogBuilderExtern(metaData,index);
//                                        console.log("Chargement MetaData apres $scope.editDialogBuilderExtern(metaData)"); 
                                        $scope.currentAction = $scope.dataCache['currentAction'];
                                        $scope.currentObject = $scope.dataCache['currentObject'];
                                        //Initialisation de l'url
                                        restService.url(angular.lowercase($scope.currentAction.entityName),angular.lowercase($scope.currentAction.model));
                                        //Appele de la fenetre modale
                                        var modalID = "";
                                        var endIndex = index;            
                                        if(endIndex==1){
                                            modalID = "myModal";
                                        }else if(endIndex==2){
                                            modalID = "globalModal";
                                        }else if(endIndex==3){
                                            modalID = "myModal1";
                                        }else if(endIndex==4){
                                            modalID = "myModal2";
                                        }
                                        $("#"+modalID).modal("toggle");
                                        $("#"+modalID).modal("show");
                                        commonsTools.hideDialogLoading();
                                    },function(error){
                                        $scope.currentAction = $scope.dataCache['currentAction'];
                                        $scope.currentObject = $scope.dataCache['currentObject'];
                                        commonsTools.hideDialogLoading();
                                        commonsTools.showMessageDialog(error);
                                    });
                        }//end else if($scope.currentAction.modal)
                    
                } 
         };
          /**
             Reception du message de changement de l'action
          **/
          $scope.$on("currentActionUpdate" , function(event , args){
               if(args.action){
                $scope.dataCache['currentObject'] = $scope.currentObject;
                $scope.dataCache['currentAction'] = $scope.currentAction;
                $scope.viewmode = args.action.viewMode;   
                $scope.calendarrecord = args.action.calendar;
                $scope.currentAction = args.action;
                var template = args.template;
                var index = args.index;
//                console.log("$scope.$on(currentActionUpdate , function(event , args) ===== "+);
                $scope.enabledVerticalMenu = args.verticalMenu;
                $scope.reset();
                $scope.initAction(template,index);
              }
           });
            $scope.$on("currentActionUpdateModal" , function(event , args){
              if(args.action){
                $scope.dataCache['currentObject'] = $scope.currentObject;
                $scope.dataCache['currentAction'] = $scope.currentAction;
                $scope.currentAction = args.action;
                $scope.currentAction.modal =true ;
                var index = args.index;
                $scope.enabledVerticalMenu = args.verticalMenu;
                var template = args.template;
                $scope.reset();
                $scope.initAction(template,index);
              }
           });
           /**
            * Reception des evenement de d'edition des etats
            */
          $scope.$on("customreport" , function(event, args){
//               console.log("customreport =========== "+angular.toJson(args.metaData)); 
               $scope.dataCache["report"] = args.report;
               $scope.editDialogBuilderExtern(args.metaData,1);
               $("#myModal").modal("toggle");
               $("#myModal").modal("show");
          });
          
         
      },
    	link : function(scope , element , attrs){
                  
                  scope.listFrameBuilder(scope.metaData);
                  //var partial =angular.element(document.querySelector('#container'));
                  //console.log("Vous etes "+partial.html());
                  // console.log("enabledVerticalMenu = "+scope.enabledVerticalMenu);
                  
    	      }
    };
    angular.module('mainApp')
        .directive('fileinput',function(){
             return{  
                 require:"^headerBuilder",
                 scope:{
                     fileinput:"=fileinput",
                     filepreview: "=filepreview"
                 },
                 //replace:true,
                 link:function(scope , element, attrs){
                        element.bind("onchange",function(changeEvent){
                           scope.fileinput = changeEvent.target.files[0];
                           //console.log("link:function(scope , element, attrs) ========= "+scope.fileinput);
                           var reader = new FileReader();
                           reader.onload = function(loadEvent){
                               scope.$apply(function(){
                                   scope.filepreview = loadEvent.target.result;
                               });
                           };
                           reader.readAsDataURL(scope.fileinput);
                        });
                    }              
            };
        });

});