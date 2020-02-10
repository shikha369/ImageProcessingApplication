runC<-function(TrainReadpath,TestReadpath,ResWritePath){
 # runC<-function(){
 # TrainReadpath="C:/Users/Shikha/Desktop/TrainingFeaturesdoc.csv";
  #TestReadpath="C:/Users/Shikha/Desktop/TrainingFeaturesdoc.csv";
  # ResWritePath="C:/Users/Shikha/Desktop/testboth/res.csv";
  #Load Libraries
  #library("Rserve")
  library("party");
  library("stringdist");
  #Rserve();
  
  #Load Training Features
  TrainData<-read.csv(file=TrainReadpath, header=TRUE, sep=",");
  devTdata<-TrainData[TrainData$wordType==1,];
  engTdata<-TrainData[TrainData$wordType==0,];
  rownames(devTdata)<-NULL
  rownames(engTdata)<-NULL
  #Load test data
  TestData<-read.csv(file=TestReadpath,header=TRUE, sep=",");
  
  #Build decision tree on Training Features
  engfit <- ctree(filename ~ deadends + boundaries+inwardCorner+outwardCorner+cone+bucket, data=engTdata);
  devfit <- ctree(filename ~ deadends + boundaries+inwardCorner+outwardCorner+cone+bucket, data=devTdata);
  
  engTrainprediction<-Predict(engfit,engTdata);
  devTrainprediction<-Predict(devfit,devTdata);
  
  engcrossClassification<-table(engTdata$filename, engTrainprediction);
  devcrossClassification<-table(devTdata$filename, devTrainprediction);
  
  #normalise
  diag(engcrossClassification)<-1
  diag(devcrossClassification)<-1
  
  
 
  
  engmat<-as.data.frame(engcrossClassification)
  devmat<-as.data.frame(devcrossClassification)
  
  englist <- levels(engmat$engTrainprediction)
  devlist <- levels(devmat$devTrainprediction)
  
  engc<-engmat[which(engmat[,3]>0),]
  devc<-devmat[which(devmat[,3]>0),]
  
  finalClass<-NULL
  
  ##c[c$prediction==list[2],1]
  
  #Testprediction<-Predict(fit,TestData)
  Testprediction<-NULL
  for(i in 1:nrow(TestData))
  {
    mined<-99
    z<-c()
    ##check for wordtype here
    if(TestData[i,3]==0) #condition for eng
    {
    Testprediction<-Predict(engfit,TestData[i,]);
    breakflag<-0;
    check<-engc[engc$engTrainprediction==Testprediction,1]
    for(j in 1:length(check))
    {
      #ce<-engTdata[engTdata$filename==check[j],27]
      ce<-engTdata[engTdata$filename==check[j],27]
      be<-engTdata[engTdata$filename==check[j],28]
      for(k in 1:length(ce))
      {
        #convert factor to character
        a<-sapply(ce[k],as.character)
        b<-sapply(TestData[i,27],as.character)
        ed<-stringdist(a,b)
        if(ed<mined)
        {
          mined<-ed;
          z<-check[j];
          
        }
        #testdata=traindata here
        #if(TestData[i,27]==ce[k])
        #if(a==b)
        if(mined==0)
        {
          breakflag=1;
          finalClass<-paste(finalClass,check[j])
          break;
        }
        
      }
      if(breakflag==1)
        break;
    }
    if(j==length(check) & breakflag==0)
    {
      #finalClass<-paste(finalClass,Testprediction)
      finalClass<-paste(finalClass,z)
      #finalClass<-paste(finalClass,"*")
    }
    
    }
   else if(TestData[i,3]==1) #condition for dev
    {
      Testprediction<-Predict(devfit,TestData[i,]);
      breakflag<-0;
      check<-devc[devc$devTrainprediction==Testprediction,1]
      for(j in 1:length(check))
      {
        ce<-devTdata[devTdata$filename==check[j],27]
        be<-devTdata[devTdata$filename==check[j],28]
        for(k in 1:length(ce))
        {
          
          #convert factor to character
          a<-sapply(ce[k],as.character)
          b<-sapply(TestData[i,27],as.character)
          ed<-stringdist(a,b)
          if(ed<mined)
          {
            mined<-ed;
            z<-check[j];
            
          }
          #testdata=traindata here
          #if(TestData[i,27]==ce[k])
          if(a==b)
          {
            breakflag=1;
            finalClass<-paste(finalClass,check[j])
            break;
          }
          
        }
        if(breakflag==1)
          break;
      }
      if(j==length(check) & breakflag==0)
      {
        finalClass<-paste(finalClass,z)
      }
      
      
    }
    }
  
  finalClass<-unlist(strsplit(finalClass,split=" "))
  #results are in finalClass(2:length(finalClass))
  finalClass<-finalClass[2:length(finalClass)]
  
  res<-table(TestData$filename, finalClass);
  mat<-as.data.frame(res)
  c<-mat[which(mat[,3]>0),]
  
  write.csv(finalClass,file=ResWritePath);
}