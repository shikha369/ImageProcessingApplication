#Load Libraries
library("Runiversal");
library("rpart");

#Load Training Features
TrainData<-read.csv(file="C:/Apache Software Foundation/Tomcat 8.0/webapps/ImageProcessingApplication/resources/trainedFeatures.csv", header=TRUE, sep=",");
#Build decision tree on Training Features
fit <- rpart(CLASS ~ BOUNDARIES + DEADENDS,method="class", data=TrainData);
#Load test data
TestData<-read.csv(file="C:/Apache Software Foundation/Tomcat 8.0/webapps/ImageProcessingApplication/resources/testFeatures.csv",header=TRUE, sep=",");
#load features to recognise characters and write into csv
recognisedCharacters<-c();
name<-c();
for(i in 1:nrow(TestData)) 
{
  test<-TestData[i,2:4];  #load testFeatureVectorData row by row
  prediction <- predict(fit, test);
  # predict response
  pred.response <- colnames(prediction)[max.col(prediction, ties.method = c("random"))]; 
  name[i]<-TestData[i,1];
  recognisedCharacters[i]<-pred.response;
}
recognisedCharacters<-cbind.data.frame(TestData[,1],recognisedCharacters);
#...print(recognisedCharacters);
#write into csv
write.table(recognisedCharacters, 
file = "C:/Apache Software Foundation/Tomcat 8.0/webapps/ImageProcessingApplication/resources/recognisedCharacters.csv",
row.names=FALSE, na="",col.names=FALSE, sep=",")


