/**
* Class: B.Sc. in Computing
* Instructor: Maria Boyle
* Description: This class is a GUI for logging into facebook and signing up
* Date: 30/03/2022
* @author Joseph Foy
*/
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Facebook extends Application{
   // declare 2nd signupButton to make its scope larger
   Button signupBtn2;
   // declare 2nd signup textFields
   TextField fName, lName, email, password;
   //declare initial login button
   Button loginBtn;
   
   @Override
   public void start(Stage primaryStage){
      BorderPane sceneLayoutLogin, sceneLayoutSignup;
     
      // this is the only pane not in a method
      TextField logInEmail, logInPw;
      Button signupBtn;
      GridPane p1;
      // top pane named p1
      p1 = new GridPane();
      p1.add(new Label("Email: "),0,0);
      p1.add(new Label("Password: "),0,1);
      p1.add(logInEmail = new TextField(),1,0);
      p1.add(logInPw = new TextField(),1,1);
      loginBtn = new Button("Log In");
      loginBtn.setMaxWidth(Double.MAX_VALUE);
      loginBtn.setBorder(new Border(new BorderStroke(
         Color.BLACK,
         BorderStrokeStyle.SOLID,
         new CornerRadii(0),
         new BorderWidths(1)
      )));
      // event handling the second sign up button
      loginBtn.setOnAction(new EventHandler<ActionEvent>(){//Anonymous Class
         @Override
         public void handle(ActionEvent e){
            String emailIn = String.valueOf(logInEmail.getText());
            String pwIn = String.valueOf(logInPw.getText());
            
            if(emailIn.trim()=="" || pwIn.trim()==""){
               System.out.println("Fields empty");
            }
            else{
               try{
                  Connection conn;
                  // Create Statement object		    
                  Statement stmt = conn.createStatement();
            	   ResultSet rs = stmt.executeQuery("SELECT password, emailaddress from facebook.user;");
                  while(rs.next() != false){
                     // Can get columns by name, or number which starts at 1            
                     String em = rs.getString("emailaddress");
                     String pw = rs.getString("password");
                     if(em.equals(emailIn) && pw.equals(pwIn)){
                        System.out.println("Login Successful. Welcome" + rs.getString("firstname")); 
                        break;
                     }
                  }
               }
               catch (SQLException e2){
         			System.out.println("Unable to save new user\n" + e2.getMessage());
         		}
            }
         }
      });
      signupBtn = new Button("Sign Up");
      signupBtn.setMaxWidth(Double.MAX_VALUE);
      signupBtn.setBorder(new Border(new BorderStroke(
         Color.BLACK,
         BorderStrokeStyle.SOLID,
         new CornerRadii(0),
         new BorderWidths(1)
      )));
      
      // colour the buttons
      loginBtn.setStyle("-fx-background-color: ALICEBLUE; ");
      signupBtn.setStyle("-fx-background-color: ALICEBLUE; ");
      
      p1.add(loginBtn,2,0);
      p1.add(signupBtn,2,1);
      
      // Break the columns of p1 into percentage widths of 25,50,25
      ColumnConstraints col1 = new ColumnConstraints();
      col1.setPercentWidth(25);
      ColumnConstraints col2 = new ColumnConstraints();
      col2.setPercentWidth(50);
      ColumnConstraints col3 = new ColumnConstraints();
      col3.setPercentWidth(25);
      p1.getColumnConstraints().addAll(col1,col2,col3);
      
      p1.setBorder(new Border(new BorderStroke(
         Color.BLACK,
         BorderStrokeStyle.SOLID,
         new CornerRadii(0),
         new BorderWidths(1)
      )));
      
      sceneLayoutLogin = new BorderPane();
      sceneLayoutLogin.setTop(p1);
      sceneLayoutLogin.setBottom(getLoginPaneBottom());
      
      sceneLayoutSignup = new BorderPane();
      sceneLayoutSignup.setLeft(getLeftPaneSignup());
      sceneLayoutSignup.setRight(getRightPaneSignup());
      
      Scene sceneLogin = new Scene(sceneLayoutLogin);
      Scene sceneSignup  = new Scene(sceneLayoutSignup);
      
      // listener class to handle buttons
      signupBtn.setOnAction(new EventHandler<ActionEvent>(){//Anonymous Class
         @Override
         public void handle(ActionEvent e){
            primaryStage.setScene(sceneSignup);      
            primaryStage.setTitle("FACEBOOK SIGNUP");
            primaryStage.show(); 
         }
      });
      
      // event handling the second sign up button
      signupBtn2.setOnAction(new EventHandler<ActionEvent>(){//Anonymous Class
         @Override
         public void handle(ActionEvent e){
            String fNameIn = String.valueOf(fName.getText());
            String lNameIn = String.valueOf(lName.getText());
            String emailIn = String.valueOf(email.getText());
            String passwordIn = String.valueOf(password.getText());
            
            if(fNameIn.trim()=="" || lNameIn.trim()=="" || emailIn.trim()=="" || passwordIn.trim()==""){
               System.out.println("Fields empty");
            }
            else{
               try{
                  FacebookDB fbDb2 = new FacebookDB();
                  String sql2 = "INSERT INTO user VALUES ('" + emailIn + "', '" + passwordIn +"', '" + fNameIn + "', '" + lNameIn +"') "+"ON DUPLICATE KEY UPDATE password='"+passwordIn+"', firstname='"+fNameIn+"', lastname='"+lNameIn+"'";
                  fbDb2.insertIntoDatabase(sql2);
               }
               catch (SQLException e2){
         			System.out.println("Unable to save new user\n" + e2.getMessage());
         		}
            }
         }
      });

      
      
      // set the initial stage
      primaryStage.setScene(sceneLogin);      
      primaryStage.setTitle("FACEBOOK");
      primaryStage.show();
   }
   
   //////////////////////////////////////////////
   public GridPane getLoginPaneBottom(){
      GridPane p2;
      // bottom pane named p2
      p2 = new GridPane();
      ImageView fbImg = new ImageView();
      fbImg.setImage(new Image("File:image/facebook.png"));
      p2.getChildren().add(fbImg);
      p2.setBorder(new Border(new BorderStroke(
         Color.BLACK,
         BorderStrokeStyle.SOLID,
         new CornerRadii(0),
         new BorderWidths(1)
      )));
      
      return p2;
   }  
   
   //////////////////////////////////////////////
   public GridPane getLeftPaneSignup(){
      GridPane pL;
      
      // left pane named pL
      pL = new GridPane();
      ImageView fbImg = new ImageView();
      fbImg.setImage(new Image("File:image/facebook.png"));
      pL.getChildren().add(fbImg);
      pL.setBorder(new Border(new BorderStroke(
         Color.BLACK,
         BorderStrokeStyle.SOLID,
         new CornerRadii(0),
         new BorderWidths(1)
      )));
      
      return pL;
   }
   
   ////////////////////////////////////////////
   public GridPane getRightPaneSignup(){
      
      GridPane pR;
      Label signupLbl;
      
      // right pane named pR
      pR = new GridPane();
      
      signupLbl = new Label("Sign Up");
      signupLbl.setFont(Font.font ("SanSerif", FontWeight.BOLD, 18));;
      signupLbl.setBorder(new Border(new BorderStroke(
         Color.BLACK,
         BorderStrokeStyle.SOLID,
         new CornerRadii(0),
         new BorderWidths(1)
      )));
      signupLbl.setMaxWidth(Double.MAX_VALUE);
      pR.add(signupLbl,0,0);
      
      fName = new TextField();
      fName.setPromptText("Enter First Name");
      lName = new TextField();
      lName.setPromptText("Enter Surname");
      email = new TextField();
      email.setPromptText("Enter Email Address");
      password = new TextField();
      password.setPromptText("Enter Password");
      
      pR.add(fName,0,1);
      pR.add(lName,0,2);
      pR.add(email,0,3);
      pR.add(password,0,4);
      
      signupBtn2 = new Button("Sign Up");
      signupBtn2.setFont(Font.font ("SanSerif", FontWeight.BOLD, 18));
      signupBtn2.setStyle("-fx-background-color: ALICEBLUE; ");
      signupBtn2.setMaxWidth(Double.MAX_VALUE);
      signupBtn2.setBorder(new Border(new BorderStroke(
         Color.BLACK,
         BorderStrokeStyle.SOLID,
         new CornerRadii(0),
         new BorderWidths(1)
      )));
      pR.add(signupBtn2,0,5);
      
      return pR;
   }

}