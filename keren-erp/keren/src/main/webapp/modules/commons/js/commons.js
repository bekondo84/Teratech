/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Module contenant tous les outils
 * @param {type} param1
 * @param {type} param2
 */
angular.module('keren.core.commons',['ngResource']);

angular.module('keren.core.commons')
        .directive('ace',['$timeout',function($timeout){
         var resizeEditor = function(editor,elem){
                  var lineHeight = editor.renderer.lineHeight;
                  var rows = editor.getSession().getLength();

                  $(elem).height(rows * lineHeight);
                  editor.resize();
                };
                return {
                  restrict:'A',
                  require:'?ngModel',
                  scope:true,
                  link:function(scope,elem,attrs,ngModel){
                    var node = elem[0];

                    var editor = ace.edit(node);

                    editor.setTheme('ace/theme/monokai');

                    ///var MarkdownMode = require('ace/mode/markdown').Mode;
                    editor.getSession().setMode('ace/mode/xml'); //new MarkdownMode()

                    // set editor options
                    editor.setShowPrintMargin(false);

                    // editor.setAutoScrollEditorIntoView(true);

                    // data binding to ngModel
                    ngModel.$render = function () {
                        editor.setValue(ngModel.$viewValue);
                        resizeEditor(editor, elem);
                    };

                    editor.on('change', function () {
                        $timeout(function () {
                            scope.$apply(function () {
                                var value = editor.getValue();
                                ngModel.$setViewValue(value);
                            });
                        });

                        resizeEditor(editor, elem);
                    });
                  }
                };
}]);
angular.module('keren.core.commons')
        .factory('commonsTools',function(){
            //Liste des contraintes
            var uniqueContraints = new Array();
            var stopTimer = 0;
            var showDialogLoadingFull = function(idElement, logo, color, opacity){
                   $('body').append("<div id="+idElement+" style='width:100%;height:100%;position:absolute;z-index:5000;text-align:center;background-color:black'></div>");
                    $('#'+idElement).append("<div id='dialogFullWindow' style='width:100%;margin:auto;margin-top:22%;color:white;text-align:center'>"+logo+"</div>");

                    //Changer le proprietes css
                    $('#'+idElement).css("opacity",opacity);
                    $('#dialogFullWindow').css("color",color);


                    //Afficher le dialog
                    $('#'+idElement).hide();
                    $('#'+idElement).fadeIn();
               };

            return {
                
                /**
                 * 
                 * @param {type} texte
                 * @param {type} color
                 * @param {type} colorContent
                 * @param {type} topPos
                 * @param {type} leftPos
                 * @returns {undefined}
                 */
               showDialogLoading :function(texte, color, colorContent, topPos,leftPos) {
                   var idElement = "dialogContent";
                    $('body').append("<div id="+idElement+" style='width:100%;height:100%;position:absolute;z-index:2000;text-align:center;'></div>");
                    $('#'+idElement).append("<div id='dialogWindow'></div>");
                    $('#dialogWindow').append("<span id='dialogWindowText' style='text-align:center;padding:8px;padding-right:16px;padding-left:16px;display:inline-block;color:white;border-radius:3px;font-size:80%;'>"+texte+"</span>");

                    //Changer le proprietes css
                    $('#'+idElement).css("top",topPos);
                    $('#'+idElement).css("left",leftPos);

                    $('#dialogWindowText').css("color",color);
                    $('#dialogWindowText').css("background-color",colorContent);

                    //Afficher le dialog
                    $('#'+idElement).fadeIn();

                    stopTimer = setTimeout(function(){

                            $('#'+idElement).fadeOut(function(){
                                    showDialogLoadingFull(idElement+"_Full","<i class='fa fa-cog fa-spin fa-3x fa-fw'></i>","white","0.2");
                                    $('#'+idElement).remove();

                                    /*setTimeout(function(){
                                            hideDialogLoading("open01");
                                    },6000);*/

                            });

                            //hideDialogLoading(idElement+"_Full");
                    },6000);
	
               },//end
               
               hideDialogLoading :function() {
                    //On stoppe le moteur
                    this.stopMoteur(stopTimer);
                    var idElement = "dialogContent";
                    //On cache
                    $('#'+idElement).fadeOut(function(){
                            $('#'+idElement).remove();
                    });

                    //On cache
                    $('#'+idElement+"_Full").fadeOut(function(){
                            $('#'+idElement+"_Full").remove();
                    });
               },
               stopMoteur : function(IdMoteur){
                   clearInterval(IdMoteur);
               },
               /**
              Affiche la notification en cas d'erreur
                **/
               notifyWindow : function(title , message ,type){
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
               },
               /**
                * Affiche fenetre alerte
                */
               alertWindow : function(title , message ,type){
                   
               },
               //Fileds Validations
                 /**
                    Validate all the fields of the forms to chack constraint validation

                  **/
                 validateFields : function(metaData,currentObject){
                       var champs = new Array();
                       uniqueContraints =  new Array();
                       if(metaData && currentObject){
                           if(metaData.columns){
                              for(var i=0 ; i< metaData.columns.length;i++){
                                  if(!metaData.columns[i].optional || metaData.columns[i].min){
                                      if(!currentObject[metaData.columns[i].fieldName]){
                                          champs.push(metaData.columns[i].fieldLabel);
                                      }
                                  }
                                  //Construction des champs pour unicite
                                  if(metaData.columns[i].unique){
                                      var pred = new Object();
                                      pred.fieldLabel = metaData.columns[i].fieldLabel;
                                      pred.fieldName =  metaData.columns[i].fieldName;
                                      pred.fieldValue = currentObject[metaData.columns[i].fieldName];
                                      uniqueContraints.push(pred);
                                  }
                              }
                            }
                            //Cas des groups
                            if(metaData.groups){
                               for(var i=0;i<metaData.groups.length;i++){
           //                        if($scope.metaData.groups[i].metaArray){
           //                            for(var j=0 ; j<$scope.metaData.groups[i].metaArray.metaData.columns.length ; j++){
           //                                if(!$scope.metaData.groups[i].metaArray.metaData.columns[j].optional || $scope.metaData.groups[i].metaArray.metaData.columns[j].min){
           //                                      champs.push($scope.metaData.groups[i].metaArray.metaData.columns[j].fieldLabel);
           //                                }
           //                            }
           //                        }
                                   //Cas des données normales
                                   if(metaData.groups[i].columns){
                                      for(var j=0 ; j< metaData.groups[i].columns.length;j++){
                                         if(!metaData.groups[i].columns[j].optional || metaData.groups[i].columns[j].min){
                                              var pred = new Object();
                                               pred.fieldLabel = metaData.groups[i].columns[j].fieldLabel;
                                               pred.fieldName =  metaData.groups[i].columns[j].fieldName;
                                               pred.value =  currentObject[metaData.groups[i].columns[j].fieldName];
                                               uniqueContraints.push(pred);
                                               // champs.push($scope.metaData.groups[i].columns[j].fieldLabel);
                                         }
                                         //Construction des champs pour unicite
                                       if(metaData.groups[i].columns[j].unique){
                                           uniqueContraints.push(metaData.groups[i].columns[j].fieldLabel);
                                       }
                                      }
                                   }
                               }
                            }
                       }
                       return champs;

                  },
                  /**
                   * Contriante unicite (appeler a pres l'apel de validateFields 
                   * @returns {commons_L14.commonsAnonym$1.uniqueContraints}
                   */
                  uniqueContraints:function(){
                      return  uniqueContraints;
                  },
                  /***
                    Construit les composants 
                 **/
                 createFromFields: function(columns,currentObject){
                       for(var i=0 ; i< columns.length;i++){
                            if(columns[i].type=='object'){
                              if(!currentObject[""+columns[i].fieldName+""]){
                                currentObject[""+columns[i].fieldName+""] = null;
                              }
                            }else if(columns[i].type=='array'){
                               if(!currentObject[""+columns[i].fieldName+""]) {
                                  currentObject[columns[i].fieldName] = new Array();
                                }
                                //console.log("createFromFields =====  "+$scope.currentObject[columns[i].fieldName]);                       

                                //object["'"+metaData.columns[i].fieldName+"'"].push($scope.createEmptyObject(metaData.columns[i].metaData));
                            }else{
                                if(!currentObject[""+columns[i].fieldName+""]) {
                                  currentObject[""+columns[i].fieldName+""] = "";
                                }
                            }
                        }
                 },

                /**
                 Create a empty object base of the metaData
                 @metaData : the description of the object
              **/
              createEmptyObject: function(metaData){            
                    var currentObject = new Object();
                     if(metaData){
                        //Cas des colonnes
                        if(metaData.columns){
                              this.createFromFields(metaData.columns,currentObject);                     
                        }
                        //Traitement des champs groups
                        if(metaData.groups){
                             //Traitement des groups
                             for(var i=0 ; i< metaData.groups.length;i++){
                                 //Cas des colonnes
                                 if(metaData.groups[i].columns){
                                    this.createFromFields(metaData.groups[i].columns,currentObject);
                                 }
                                 //cas des metaArray
                                 if(metaData.groups[i].metaArray){
                                     currentObject[metaData.groups[i].metaArray.fieldName] = new Array();
                                 }
                             }
                        }

                     } 
                    return currentObject;
                 },
            /**
             * Calcul la date end fonction de la date et heure
             * @param {type} date
             * @param {type} hours
             * @returns {undefined}
             * @date : date de debut
             * @hours: duree du au format hh:mm:ss
             */
            computeDate:function(date , hours){
                if(angular.isDate(date)){

                    var heu = 0;
                    var min = 0;
                    var sec = 0 ;
                    if(angular.isString(hours)){
                        var parts = hours.split(":");               
                        for(var i=0 ;i<parts.length;i++){
                            if(i==0){
                                heu = Number(parts[i]);
                            }else if(i==1){
                                min = Number(parts[i]);
                            }else if(i==2){
                                sec = Number(parts[i]);
                            }
                        }
                    }//end if(angular.isString(hours))
                    var result = date.getTime()+ (heu*60*60+min*60+sec)*1000;
                    return new Date(result);
                }

            },
         /**
          * 
          * @param {type} date
          * @returns {unresolved}
          */
         convertToLocalDate:function(date){
                 //var date = new Date();  
                var d = date.getDate();
                var m = date.getMonth();
                var y = date.getFullYear();
                var h = date.getHours()<9 ? "0"+date.getHours():date.getHours();
                var m2 = date.getMinutes()<9 ? "0"+date.getMinutes():date.getMinutes();
                var s = date.getSeconds();
               var dateString = new String();
               dateString = y+"-"+m+"-"+d+"T"+h+":"+m2;
               return dateString;
         },
         
         convertDateToString:function(date){
               var d = date.getDate();
                var m = date.getMonth();
                var y = date.getFullYear();
                var h = date.getHours()<9 ? "0"+date.getHours():date.getHours();
                var m2 = date.getMinutes()<9 ? "0"+date.getMinutes():date.getMinutes();
                var s = date.getSeconds();
               var dateString = new String();
               dateString = d+"-"+m+"-"+y+" "+h+":"+m2;
         },
         /**
             Verifie que le tableau contient un element
             @array : tableau de données
             @item : element
          **/
          contains: function(array , item){
               if(!angular.isDefined(array)){
                  return false;
               }

               for(var i= 0 ; i<array.length;i++){

                   if(array[i].id == item.id){
                      return true;
                   }
               }

               return false;
          },
          /**
           * 
           * @param {type} array
           * @param {type} item
           * @returns {undefined}
           */
          getIndex: function(array,item){
              if(!angular.isDefined(array)){
                  return false;
               }
//               console.log("commonsTool.getIndex ::::::::::::::: "+angular.toJson(array)+" === item :: "+angular.toJson(item));
               for(var i= 0 ; i<array.length;i++){
                   if(array[i].id == item.id){
                      return i;
                   }
               }
               
          },
          /**
            * 
            * @param {type} array
            * @param {type} item
            * @returns {undefined}
            */
          removeFromArray: function(array , item){
               if(angular.isDefined(array)){
                  
                  for(var i=0 ; i<array.length;i++){
                     if(array[i].id == item.id){
                        array.splice(i , 1);
                     }
                  }
                  //console.log(array+" ====== "+item);                             
              }
          },
          /**
           * 
           * @param {type} metaData
           * @returns {metaData.groups.metaArray|metaData.columns|Object}
           */
          metaDataMapBuilder : function(metaData){
              var map = new Object();
               if(metaData==null||!angular.isDefined(metaData)){
                   return map;
               }
               //Traitement de la metaData
               if(metaData.columns){
                 for(var i=0 ; i<metaData.columns.length;i++){
                      map[metaData.columns[i].fieldName] = metaData.columns[i];
                 }
               }//end if(metaData.columns){
               //Cas des groups
               if(metaData.groups){
                 for(var i=0 ; i<metaData.groups.length;i++){
                     //Cas des columns
                     if(metaData.groups[i].columns){
                         var columns = metaData.groups[i].columns;
                         for(var j=0 ; j<columns.length;j++){
                             map[columns[j].fieldName] = columns[j];
                         }
                     }
                     //Cas des metaArray
                     if(metaData.groups[i].metaArray){
                         map[metaData.groups[i].metaArray.fieldName] = metaData.groups[i].metaArray;
                     }
                 }
               }//end if(metaData.groups){
                return map;
            },

            xmlViewParser : function(data,tree,meta){
                 if(meta==null || !angular.isDefined(meta)){
                      return null;
                 }
                 var map = this.metaDataMapBuilder(meta);                 
                 var metaData = meta;
                 if(data){
                     metaData = new Object();
                     metaData['entityName'] = meta['entityName'];
                     metaData['moduleName'] = meta['moduleName'];
                     metaData['editTitle'] = meta['editTitle'];
                     metaData['listTitle'] = meta['listTitle'];
                     metaData['createonfield'] = meta['createonfield'];
                     metaData['desablecreate'] = meta['desablecreate'];
                     metaData['header'] = new Array();
                     metaData['columns'] = new Array();
                     metaData['groups'] = new Array();
                     var container = document.createElement('div');
                     container.innerHTML = data;
                     var form = $(container).find('formRecord');
                     //parametres descriptif de la vue
                     var label = $(form).attr('label');
                      if(label){
                          metaData['editTitle'] = label;
                      }
                     //Traitement des headers
                     var header = $(container).find('header');
                     if(header){
                          //traitement des buttons
                          var buttons = header.find('button');
                          if(buttons){
                              for(var i=0 ; i<buttons.length;i++){
                                var button = buttons.eq(i);
                                 var column = new Object();
                                  column['type'] = button.attr('type');
                                  column['search'] = false;
                                  column['target'] = button.attr('class');;
                                  column['fieldName'] = button.attr('name');
                                  column['fieldLabel'] = button.attr('label');
                                  column['value'] = null;
                                  column['optional'] = null;
                                  column['unique']=null;
                                  column['updatable'] = null;
                                  column['min']=null;
                                  column['max']=null;
                                  column['pattern']=null;
                                  column['sequence']=null;
                                  column['metaData']=null;
                                  column['colsequence']=null;
                              }//end for(var i=0 ; i<buttons.length;i++) 
                          }//end if(buttons)
                       }//end if(header){

                     //Traitement du sheet
                     var sheet = $(container).find('sheet');
                     //traitement des fields descendant du sheet
                     var fields = $(sheet).find('field');
                     var sequence = 1;
                     for(var i=0;i<fields.length;i++){
                        var name = fields.eq(i).attr('name');
                        var target = fields.eq(i).attr('target');
                        var columns = metaData['columns'];
                        //Si le champs name est definie
                        if(name){
                            columns[i] = map[name];
                            var column = columns[i];
                            column['sequence'] = sequence;
                            column['search'] = false;
                            if(target) {                            
                                column['target']=target;
                            }
                         }
                         sequence = sequence+1 ;
                     }
                     //Traitement des groups 
                     var groups = $(sheet).find('group'); 
                     for(var i=0;i<groups.length;i++){
                         var group = groups.eq(i);
                         var name = group['name'];
                         var label = group['label']
                         if(name){
                             var groupe = new Object();
                             groupe['name'] = name;
                             groupe['label'] = label;
                             groupe['sequence'] = i;
                             groupe['columns'] = new Array();
                             groupe['metaArray']=null;
                             //Traitement des champs
                             var fields = $(group).find('field');
                             for(var j=0 ; j<fields.length;j++){
                                 var field = fields.eq(j);
                                 var name = fields.eq(i).attr('name');
                                 var target = fields.eq(i).attr('target');
                                 if(name){
                                    var elem = map[name];
                                    elem['sequence'] = sequence;
                                    elem['search'] = false;
                                    if(target) {                            
                                        elem['target']=target;
                                    }//end if(target)
                                    if(elem['type']=='array'){
                                        groupe['metaArray'] = elem;
                                    }else{
                                         groupe['columns'].push(elem);
                                    }//end if(elem['type']=='array')                                
                                 }//end if(name)
                                 
                             }//end for(var j=0 ; j<fields.length;j++)
                         }//end if(name)
                     }//end for(var i=0;i<groups.length;i++)
                  }//end if(data)
                 //Traitement de la fenetre liste
                 //console.log(tree);
                 if(tree){
                     container = document.createElement('div');
                     container.innerHTML = tree;
                     var form = $(container).find('treeRecord');
                     //parametres descriptif de la vue
                     var label = $(form).attr('label');
                      if(label){
                          metaData['listTitle'] = label;
                      }
                     var fields = $(container).find('field');
                     for(var i=0;i<fields.length;i++){
                        var name = fields.eq(i).attr('name');
                        var target = fields.eq(i).attr('target');
                        var columns = metaData['columns'];
                        //Si le champs name est definie
                        if(name){
                            //columns[i] = map[name];
                            var column = map[name];
                            column['colsequence'] = i;
                            column['search'] = true;                            
                         }                         
                     }//end for(var i=0;i<fields.length;i++)
                     //console.log(fields.innerHTML);
                 }//end if(tree)
                 //console.log(angular.toJson(metaData));
                 return metaData;
            },
            /**
             * 
             * @param {type} error
             * @returns {undefined}
             */
            showMessageDialog:function(error){
//                    var errorobj = angular.fromJson(angular.toJson(error));
//                    console.log(angular.toJson(errorobj.headers));
                    var viewElem =  document.createElement('div'); //;
                    viewElem.setAttribute('id' , 'gmodalbody');
                    var content =  document.createElement('div');
                    content.setAttribute('style','height:300px;overflow:auto;');
                    //console.log(angular.toJson(error.data));
                    if(error.data){
                        content.innerHTML = error.data;
                    }else{
                        content.innerHTML = error;
                    }
                    viewElem.appendChild(content);
                    //Construction du footer
                    var footerDiv = document.createElement('div');
                    footerDiv.setAttribute('class' , 'modal-footer');
                    footerDiv.setAttribute('id' , 'gmodalfooter');
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
                    titleheader.setAttribute('id','gmodaltitle');
                    titleheader.appendChild(document.createTextNode("Erreur Serveur : "+error.status));                    
                    var items = $(document).find("div");
                    for(var i=0; i<items.length;i++){               
                        if(items.eq(i).attr("id")=="gmodalbody"){
                              items.eq(i).replaceWith(viewElem);                              

                        } else if(items.eq(i).attr("id")=="gmodalfooter"){
                            items.eq(i).replaceWith(footerDiv);
                        } 
                    } 


                    items = $(document).find("h4");
                    for(var i=0; i<items.length;i++){               
                        if(items.eq(i).attr("id")=="gmodaltitle"){
                              items.eq(i).replaceWith(titleheader);                                     
                        } 
                    } 
                    //Appele de la fenetre modale
                    $("#globalModal").modal("toggle");
                    $("#globalModal").modal("show");
                   
            },
            /**
             * 
             * @param {type} idFileElement
             * @param {type} idDivImageContent
             * @param {type} idApercuImageContent
             * @returns {undefined}
             */
            gererChangementImage:function(idFileElement ,idDivImageContent ,idApercuImageContent){
                //Initiallisation du tableau des images
                var files = new Array();
                document.querySelector('#'+idFileElement).onchange = function() {
//		console.log("Images Arrays ::::::::::::::::::::: ");	
		var fileInput = document.querySelector('#'+idFileElement);
		var allowedTypes = ['png', 'jpg', 'jpeg', 'gif'];
				
		files = this.files;      
		var filesLen = files.length;      
		var imgType;       

		for (var i = 0 ; i < filesLen ; i++) {              

			imgType = files[i].name.split('.');       

			imgType = imgType[imgType.length - 1].toLowerCase(); // On utilise toLowerCase() pour éviter les extensions en majuscules             

			if(allowedTypes.indexOf(imgType) != -1) { // Le fichier est bien une image supportée, il ne reste plus qu'à l'afficher                  
				
				//On affiche l'appercu				
				this.createThumbnail(files[i],idDivImageContent,idApercuImageContent);
				
			}else{
				
				//Code
			}           
		}
              };
              
              return files;
            },
            objectToNumber:function(data){
                if(data){
                    var value = new String();
                    for(var key in data){
                        value +=data[key];
                    }
                   return new Number('"'+value.trim()+'"');
                }
            },
            /**
             * 
             * @param {type} meta
             * @param {type} fieldName
             * @returns {undefined}
             */
            getMetaField:function(meta,fieldName){
                console.log("getMetaField === "+fieldName+" == "+angular.toJson(metav));
                if(meta && fieldName){
                    for(var i=0 ; i<meta.columns.length;i++){
                        if(meta.columns[i].fieldName==fieldName){
                            return meta.columns[i];
                        }
                    }//end for(var i=0 ; i<meta.columns.length;i++)
                    for(var i=0;i<meta.groups.length;i++){
                        for(var j=0;j<meta.groups[i].columns.length;j++){
                            if(meta.groups[i].columns[j].fieldName==fieldName){
                                return meta.groups[i].columns[j];
                            }
                        }
                        if(meta.groups[i].metaArray&&meta.groups[i].metaArray.fieldName==fieldName){
                           return meta.groups[i].metaArray;
                        }//end if(meta.groups[i].columns[j].fieldName==fieldName)
                    }//end for(var i=0;i<meta.groups.length;i++)
                }
            },
            /**
             * 
             * @param {type} file
             * @param {type} idDivImageContent
             * @param {type} idApercuImageContent
             * @returns {undefined}
             */
            createThumbnail:function(file,idDivImageContent,idApercuImageContent){
                var reader = new FileReader();       
		reader.onload = function() {                
					
			var imgElement = document.querySelector('#'+idApercuImageContent);      
			 imgElement.src = this.result;          

		};        
		reader.readAsDataURL(file);
            },
            /**
             * Somme la valeur d'une columns
             * @param {type} fieldName:fields name type :number
             * @param {type} datas:
             * @returns {undefined}
             */
            sumTableField:function(fieldName,datas){
                if(datas && datas.length>0){                    
                    var data = datas[0];                    
                    if(data[fieldName]!=null && angular.isNumber(data[fieldName]) ){                        
                        var total = 0;
                        for(var i=0;i<datas.length;i++){           
                            data = datas[i];
                            total += data[fieldName];
                        }//end for(var i=0;i<datas.length;i++)
//                        console.log("$scope.tableListComponent === "+fieldName+" === "+total);
                        return total;
                    }//end if(angular.isNumber(data[fieldName]))
                    
                }else{return 0;}//end if(datas && datas.length>0)
                
            },
            /**
             * Sum the table datas with rhe express
             * @param {type} expr:['tva','*','quantite']
             * @param {type} datas
             * @returns {undefined}
             */
            sumListExpr:function(expr, datas){
                var exprA = expr.split(','); 
                if(datas && datas.length>0){
                     if(exprA.length==1){
                        var data = datas[0];
                        if(angular.isNumber(data[exprA[0]])){
                            var total = 0;
                            for(var i=0;i<datas.length;i++){
                                var data = datas[i];
                                 total += data[exprA[0]];
                            }//end for(var i=0;i<datas.length;i++)   
                            return total;
                        }else{
                            return exprA[0];
                        }
                    }else{
                        var total = 0;
                        for(var i=0;i<datas.length;i++){
                            var value=this.sumExpr(exprA,datas[i]);
                            if(value && angular.isNumber(value)){
                                total += this.sumExpr(exprA,datas[i]);
                            }
                        }//end for(var i=0;i<datas.length;i++)
                    }//end if(exprA.length==1)
                    
                    
                    return total;
                }else{return 0;}//end if(datas && datas.length>0)
            },
            sumExpr:function(expr,data){
                if(data && expr.length>0){
                    var expEval = new String();
                    for(var i=0;i<expr.length;i++){
                        if(expr[i]=='*'||expr[i]=='-'||expr[i]=='+'||expr[i]=='/'||expr[i]=='%'||expr[i]=='('||expr[i]==')'){
                            expEval += expr[i];
                        }else{
                            if(angular.isNumber(data[expr[i]])){
                                expEval += data[expr[i]];
                            }else{                                 
                                    expEval += expr[i];                                
                            }//end if(data[expr[i]]&&angular.isNumber(data[expr[i]]))
                        }
                    }//end for(var i+0;i<expr.length;i++){
                    var result = eval(expEval.toString());
//                    console.log("sumListExpr:function(expr, datas)) === "+angular.toJson(expr)+" ===  ===  === "+expEval+" === "+result);
                    return result;
                }//end if(data && expr.length>0)
            },
            /**
             * Construit le pied de table
             * @param {type} script
             * @param {type} datas
             * @returns {undefined}
             */
            tableFooterBuilder:function(script , datas,id){
                var container = document.createElement('tfoot');
                container.setAttribute("id",id);
                container.innerHTML = script;
               //parcours des tr lignes
//               console.log("tableFooterBuilder ===  === "+container.childNodes.length+" ********* "+container.innerHTML);
                for(var i=0;i<container.childNodes.length;i++){
                    //traitement des colonnes
                    var rowNode = container.childNodes[i];
                     if(rowNode.tagName=='TR'){
                        for(var j=0;j<rowNode.childNodes.length;j++){
                            var colNode = rowNode.childNodes[j];
                            if(colNode.tagName=='TD'){
//                                console.log("tableFooterBuilder ===  ===  ********* "+colNode.tagName+" === "+colNode.textContent);
                                var value = colNode.textContent;                        
                                if(value!=null&&value!=""){
                                    colNode.textContent = this.sumListExpr(value,datas);
                                }//end if(value!=null&&value!="")
                            }//end if(colNode.tagName=='TD')
                        }//end for(var j=0;j<rowNode.childNodes.length;j++){
                      }//end if(rowNode.tagName=='TR'){
               }//end for(var i=0;i<container.childNodes.length;i++)
                return container;
            },
            /**
             * 
             * @param {type} metaData
             * @param {type} data
             * @param {type} model
             * @returns {undefined}
             */
            sumFooterTableBuilder:function(metaData , data,model,id){
//                console.log("commonsTool. sumFooterTableBuilder === "+model+"========"+angular.toJson(data));
                    var fieldnames = new Array();
                    for(var i = 0 ; i < metaData.columns.length;i++){
                        if(angular.isDefined(metaData.columns[i].search) && metaData.columns[i].search){
                          fieldnames.push(metaData.columns[i].fieldName);                          
                        }
                    }
                    //Traitement  des champs des groupes
                    if(metaData.groups&&metaData.groups.length>0){
                        for(var i=0;i<metaData.groups.length;i++){
                            //Cas des columns
                            if(metaData.groups[i].columns&&metaData.groups[i].columns.length>0){
                                 for(var j = 0 ; j < metaData.groups[i].columns.length;j++){
                                   if(angular.isDefined(metaData.groups[i].columns[j].search) && metaData.groups[i].columns[j].search){
                                     fieldnames.push(metaData.groups[i].columns[j].fieldName);                                     
                                   }//end  if(angular.isDefined(metaData.groups[i].columns[j].search) && metaData.groups[i].columns[j].search)
                               }//end for(var j = 0 ; j < metaData.groups[i].columns.length;j++)
                            }//end if(metaData.groups[i].columns&&metaData.groups[i].columns.length>0)                     
                        }//end for(var i=0;i<metaData.groups.length;i++)
                    }
                    //Construction du pied
                    if(fieldnames.length>0){
                         var sources = model.split(".");                        
                        var footerElem = document.createElement('tfoot');
                        footerElem.setAttribute("id",id);
                        if(fieldnames.length>0){
                        for(var i=0 ;  i<fieldnames.length ; i++){
                                var thelem = document.createElement('th');
                                footerElem.appendChild(thelem);                            
//                                console.log("commonsTool. sumFooterTableBuilder === "+model+"===="+sources[0]+"===="+angular.toJson(data));
                                var total = this.sumTableField(fieldnames[i],data[sources[sources.length-1]]);
                                if(angular.isNumber(total)){
                                    thelem.appendChild(document.createTextNode(total));
                                    thelem.setAttribute('class','text-right');
                                }
                        }//end for(var i=0 ;  i<fieldnames.length ; i++)
//                        tableElem.appendChild(footerElem);
                    }//end if(fieldnames.length>0)
               }//end if(fieldnames.length>0)
               return footerElem;
            },
            /**
             * Build for from dashboard entry data
             * @param {type} data
             * @returns {undefined}
             */
            dashboardEntryFormBuilder:function(data){
                var formElem = document.createElement('form');
//                container.appendChild(formElem);
                formElem.setAttribute('class','form-inline');
                for(var i=0 ; i<data.fields.length;i++){
                    var field = data.fields[i];
                    var divElem = document.createElement("div");
                    divElem.setAttribute("class","form-group  col-sm-12  col-md-12");
                    divElem.setAttribute("style","margin-top: 10px;");
                    formElem.appendChild(divElem);
                    var labelElem_1 = document.createElement("label");
                    divElem.appendChild(labelElem_1);
                    labelElem_1.setAttribute("for",field.fieldName);
                    labelElem_1.setAttribute("class","col-sm-6  col-md-6");
                    labelElem_1.appendChild(document.createTextNode(field.fieldLabel));
                    var labelElem_2 = document.createElement("label");
                    divElem.appendChild(labelElem_2);
                    labelElem_2.setAttribute("for",field.fieldName);
                    labelElem_2.setAttribute("class","col-sm-4  col-md-4");
                    labelElem_2.appendChild(document.createTextNode(field.fieldValue));
                    var butElem = document.createElement("button");
                    divElem.appendChild(butElem);
                    butElem.setAttribute("class","col-md-2 col-sm-2 btn btn-default btn-sm");
                    butElem.appendChild(document.createTextNode("Détail"));
                    butElem.setAttribute("ng-show",field.activalink);
                    butElem.setAttribute("ng-click" , "dashboardEntryBtn('"+field.model+"','"+field.entity+"' , '"+field.method+"')");
                }//end for(var i=0 ; i<data.length;i++)
                return formElem;
            },
            dashboardEntryBarBuilder:function(parentID , data){
                var bardata = new Object();
                bardata.type = "column";
                bardata.dataPoints = new Array();                
                for(var i=0 ; i<data.fields.length;i++){
                    bardata.dataPoints.push({label:data.fields[i].fieldLabel,y:data.fields[i].fieldValue});
                }//end for(var i=0 ; i<data.fields.length;i++)
                var bararray = new Array();
                bararray.push(bardata);
                // Construct options first and then pass it as a parameter
                var options = {
                        animationEnabled: true,
                        title: {
                                text: ""
                        },
                        data:bararray
                };
                $("#"+parentID).html("");
                $("#"+parentID).CanvasJSChart(options);
//                return divElem;
            },
            dashboardEntryPieBuilder:function(parentID , data){
                var bardata = new Object();
                bardata.type = "pie";
                bardata.startAngle= 45;
                bardata.indexLabel = "{label} ({y})";
                bardata.yValueFormatString = "#,##0.#"%"";
                bardata.dataPoints = new Array();                
                for(var i=0 ; i<data.fields.length;i++){
                    bardata.dataPoints.push({label:data.fields[i].fieldLabel,y:data.fields[i].fieldValue});
                }//end for(var i=0 ; i<data.fields.length;i++)
                var bararray = new Array();
                bararray.push(bardata);
                // Construct options first and then pass it as a parameter
                var options = {
                        animationEnabled: true,
                        title: {
                                text: ""
                        },
                        data:bararray
                };
                $("#"+parentID).html("");
                $("#"+parentID).CanvasJSChart(options);
            },
            dashboardEntryLineBuilder:function(parentID , data){
                var bardata = new Object();
                bardata.type = "spline";
                bardata.dataPoints = new Array();                
                for(var i=0 ; i<data.fields.length;i++){
                    bardata.dataPoints.push({label:data.fields[i].fieldLabel,y:data.fields[i].fieldValue});
                }//end for(var i=0 ; i<data.fields.length;i++)
                var bararray = new Array();
                bararray.push(bardata);
                // Construct options first and then pass it as a parameter
                var options = {
                        animationEnabled: true,
                        title: {
                                text: ""
                        },
                        data:bararray
                };
                $("#"+parentID).html("");
                $("#"+parentID).CanvasJSChart(options);
            },dashboardEntryUnkownBuilder:function(parentID , data){
                var divElem = document.createElement("div");
                divElem.appendChild(document.createTextNode("Unkown options ..."));
                return divElem;
            },
            /**
             * 
             * @param {type} data
             * @returns {undefined}
             */
            dashboardEntryBuilder:function(parentID,data){
                if(data.type=='data'){
                    return this.dashboardEntryFormBuilder(data);
                }else if(data.type=='bar'){
                    return this.dashboardEntryBarBuilder(parentID,data);
                }else if(data.type=='pie'){
                    return this.dashboardEntryPieBuilder(parentID,data);
                }else if(data.type=='line'){
                    return this.dashboardEntryLineBuilder(parentID,data);
                }else{
                    return this.dashboardEntryUnkownBuilder(parentID,data);
                }
            },
            /**
             * 
             * @param {type} data:dash bord datat
             * @returns {undefined}
             */
            dashboardBuilder:function(data){
                if(data){                                                            
                    var divElem = document.createElement('div');
                    divElem.setAttribute("class","panel panel-primary kanban-col");
                    divElem.setAttribute("style","margin-bottom:7px;padding-left: 0px;margin-right: 10px;width: 49%;");
                    var headElem = document.createElement("div");
                    divElem.appendChild(headElem);
                    headElem.setAttribute("class","panel-heading col-sm-12 col-md-12");
                    headElem.appendChild(document.createTextNode(data.label));
                    var iElem = document.createElement("i");
                    iElem.setAttribute("class" ,"fa fa-2x fa-plus-circle pull-right");
                    headElem.appendChild(iElem);
                    var actionElem = document.createElement("div");
                    headElem.appendChild(actionElem);
                    actionElem.setAttribute("class","btn-group  dropdown pull-right");
                    actionElem.setAttribute("role","group");
                    actionElem.setAttribute("aria-label","group 2");
                    var buttonElem = document.createElement("button");
                    actionElem.appendChild(buttonElem);
                    buttonElem.setAttribute("class","btn btn-primary dropdown dropdown-toggle btn-sm");
                    buttonElem.setAttribute("type","button");
                    buttonElem.setAttribute("data-toggle","dropdown");
                    buttonElem.setAttribute("aria-haspopup","false");
                    buttonElem.setAttribute("aria-aria-expanded","true");
                    buttonElem.appendChild(document.createElement("Plus"));
                    var spanElem = document.createElement("span");
                    buttonElem.appendChild(spanElem);
                    spanElem.setAttribute("class","caret");
                    var ulElem = document.createElement("ul");
                    actionElem.appendChild(ulElem);
                    ulElem.setAttribute("class","dropdown-menu");
                    ulElem.setAttribute("role","menu");
                    ulElem.setAttribute("aria-labelledby","dashboardbtn");
                    //Liste des menus
                    for(var i=0 ;i<data.entries.length;i++){
                        var entry = data.entries[i];
                        var liElem = document.createElement("li");
                        ulElem.appendChild(liElem);
                        liElem.setAttribute("role","presentation");
                        var aElem = document.createElement("a");
                        liElem.appendChild(aElem);
                        aElem.setAttribute("role","menuitem");aElem.setAttribute("tabindex","1");
                        aElem.setAttribute("href","#");
                        aElem.setAttribute("ng-click","showEntrypanel('"+data.code+"','"+entry.code+"')");
                        aElem.appendChild(document.createTextNode(entry.label));
                    }//end entries
                    //Body of the dashboard
                    var bodyElem = document.createElement("div");
                    divElem.appendChild(bodyElem);
                    bodyElem.setAttribute("class","panel-body col-sm-12 col-md-12");
                    bodyElem.setAttribute("style","padding: 0px;");
                    var divElem2 = document.createElement("div");
                    bodyElem.appendChild(divElem2);
                    divElem2.setAttribute("class","kanban-centered");
                    divElem2.setAttribute("style","padding:0px;");
                    var artElem = document.createElement("article");
                    divElem2.appendChild(artElem);
                    artElem.setAttribute("draggable","true");
                    artElem.setAttribute("style","height: 150px;");
                    var container = document.createElement("div");                    
                    container.setAttribute("id",data.code);
                    container.setAttribute("style","height: 100%; width: 100%;");
                    var dashentry =this.dashboardEntryBuilder(data.code,data.entries[0]);
                    if(dashentry){
                        container.appendChild(dashentry);
                    }
                    artElem.appendChild(container);
//                    console.log(" $scope.initAction ===== "+divElem.innerHTML);
                    return divElem;
                }//end if(data)
            },
            /**
             * Construction du panel tableau de bord
             * @param {type} data
             * @returns {undefined}
             */
            dashboardContainerBuilder:function(data){
                var divElem = document.createElement("div");
                divElem.setAttribute("class","row");
                divElem.setAttribute("style","padding-left:  10px;padding-right:  10px;");
                for(var i=0;i<data.dashboards.length;i++){
                    if(data.dashboards[i]){                        
                        divElem.appendChild(this.dashboardBuilder(data.dashboards[i]));                        
                    }//end if(data.dashboards[i])
                }//end for(var i=0;i<data.dashboards.length;i++)
                return divElem;
            },
            /**
             * 
             * @param {type} date
             * @returns {undefined}
             */    
             formatDat:function(date){
                if(date){
                    var today = new Date();
                    if(today.getDate()==date.getDate()&&today.getMonth()==date.getMonth()&&today.getYear()==date.getYear()){
                        return date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
                    }else{
                        return date.toDateString();
                    }
                }
            },
            
            /**
             * 
             * @param {type} id
             * @returns {undefined}
             */
            getDashoardEntryDataFromID:function(data , dashid,entryId){
//                console.log("getDashoardEntryDataFromID ::: "+angular.toJson(data));
                if(data){
                    if(data.dashboards && data.dashboards.length>0){
                        for(var i=0 ; i<data.dashboards.length;i++){
                            var dash = data.dashboards[i];
                            if(dash.code && dash.code==dashid){
                                for(var j=0 ;j<dash.entries.length;j++){
                                    var entry = dash.entries[j];
                                    if(entry.code==entryId){
                                        return entry;
                                    }
                                }
                            }//end if(dash.code && dash.code==dashid){
                        }//end for(var i=0 ; i<data.dashboards.length;i++)
                    }//end if(data.dashboards && data.dashboards.length>0)
                    return null;
                }//end if(data)
                return null ;
            }  ,
            /**
             * 
             * @param {type} model
             * @returns {undefined}
             */
            keygenerator:function(model){
                var parts= model.split(".");
                var key = "";
                for(var i=1 ; i<parts.length;i++){
                    if(i==1){
                        key = parts[i];
                    }else{
                        key+=parts[i];
                    }
                }
                return key;
            }
                //end  Fields validations
            };
            
        });
/**
 * Tools for Rest
 */
angular.module('keren.core.commons')
.factory("backendService" , function($http ,$resource , $location){
    var urlPath = "";
    //Resource rest pour l'interaction avec le back end
    var restResource = null;
     return{
            /**
             Build the restName base of the entityName
            **/
            url:function(entityName,moduleName){
                 urlPath = "http://"+$location.host()+":"+$location.port()+"/"+moduleName+"/"+entityName+"/";
                 restResource = $resource(urlPath+":path/:first/:max/:propertyname/:id"
                       ,{path:'@path',first:'@first',max:'@max',id:'@id'}
                       ,{search:{
                                    method:'GET',
                                     isArray:true,
                                    params:{ first:'@first',
                                        max:'@max',
                                        path:'filter'
                                      }                              
                                 },
                          update:{
                               method:'PUT'                               
                          }
                   });
                 //console.log("restService == "+urlPath);
                 return urlPath;
            },
            /**
               Return the metaData of the entity
            **/
            getMetaData:function(){
                  if(angular.isDefined(restResource)){
                     return  restResource.get({path:'meta'});
                  }
            },
            /**  
               Cancel etity
            **/
            cancel:function(entity){

            },
            /**
              Save entity in the back data store
              @entity: entity to save
            **/
            save: function(entity){
                //alert("Super vous avez appele le service restService avec :"+entity+" :::: "+urlPath);  
                 return  restResource.save(entity);
            },

            /**
              Save an array of entity in the back data store
              @entity: entities : array of entity to save
            **/
            saveAll: function(entities){

            },
            /**
              update entity in the back data store
              @entity: entity : entity to update
            **/
            update: function(entity){
                 //console.log("=========================== "+angular.toJson(entity));
                 return restResource.update(entity);
            },
           /**
              delete entity in the back data store
              @entity: entity : entity to delete
            **/
           delete:function(entity){
              return restResource.delete({id:entity.id});
           },
           /**
             Supprime la liste des entites
             @entities: array of objects
           **/
           deleteAll:function(entities){
               var ids = new Array();
                for(var i=0;i<entities.length;i++){
                    ids.push(entities[i].id);
                }
               //console.log()
               $http.defaults.headers.common['ids']=angular.toJson(ids);
                return restResource.delete();
           },
           /**
              find entity in the back data store with a specific ID
              @entity: id : id of the entity to find
            **/
           findById:function(id){
                 return restResource.get({id:id,path:'byid',propertyname:'id'});
           },
           /**
            * Retourn un boolean 
            * true : la contrainte d'unicite est verifier
            * false : la contrainte d'unicite est viol�e
            */
           uniqueProperty:function(propertyName , vale){
               
           },
           /**
            * Retourn un boolean 
            * true : la contrainte d'unicite est verifier
            * false : la contrainte d'unicite est viol�e
            */
           uniqueProperties:function(properties){
               $http.defaults.headers.common['properties']=angular.toJson(properties);
               //console.log(" uniqueProperties == "+angular.toJson(properties));
                if(angular.isDefined(restResource)){
                    return  restResource.query({path:'unique'});
                 }
           },
           /**
              find all entities in the back data store 
              
            **/
           findAll:function(){
               return restResource.query();
           },
           /**
              find entity in the back data store with a specific ID
              @entity: id : id of the entity to find
            **/
           findByUniqueProperty:function(propertyName , value){

           },
           /**
              find entities which match a specific criteria in the back data store 
              @predicats: array of criteria({fieldName:name,fieldValue:value ,criteria:EQUAL}
              @firstResult:the index of the first result
              @maxResult : the max number of Items of the result
            **/
           filter:function(predicats ,firstResult , maxResult){
               $http.defaults.headers.common['predicats']= angular.toJson(predicats);               
                if(angular.isDefined(restResource)){
                    return  restResource.search({path:'filter',first:firstResult,max:maxResult});
                 }
           },
           /**
             return the number of items which match the specific criteria
            @predicats: array of criteria({fieldName:name,fieldValue:value ,criteria:EQUAL}
           **/
           count:function(predicats){
               $http.defaults.headers.common['predicats']= angular.toJson(predicats);               
                if(angular.isDefined(restResource)){
                    return  restResource.get({path:'count'});
                 }
           },
           /**
            * Upload ressource to the server
            * @param {type} files
            * @returns {undefined}
            */
           uploadFile:function(files){
               //URL de la resource responsable de transfert du fichier
               var url = "http://"+$location.host()+":"+$location.port()+"/kerencore/resource/upload";
               var fd = new FormData();
               //Take the first select 
               for(var i=0;i<files.length;i++){
                   fd.append("file_"+(i+1),files[i]);               
               }
               return $http.post(url,fd
                    ,{withCredentials:true,headers:{'Content-Type':undefined},
                       transformRequest: angular.identity});
               
           }

     };
});