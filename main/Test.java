///*
// *  OSSD Asignment 1 - The Chase
// *  Charles Yim - S3570764
// *  Jacob Paris - S3238163
// *  Chen Liu- S3481556
// *  Taison Eady - S3282633
// */
//
//package main;
//
//import controllers.*;
//import java.util.Arrays;
//import models.*;
//import models.Character;
//import models.Explorer.Hero;
//import views.*;
//
//public class Test {
//    
//    public void testController(){
//       GameController gameController = new GameController();
//       //Player guardian = gameController.getPlayerController().newPlayer("Guardian");
//       Player explorer = gameController.getPlayerController().newPlayer("Explorer");
////        
////        System.out.println(guardian.getName());
////        System.out.println(explorer.getName());
//
//        gameController.setCurrentPlayer(explorer);
//        
//        UnitController unitController = new UnitController(gameController);
//
//        Character Hero = new Hero(9,9);
//        
////        for(int i=0; i < unitController.movable(Hero).length; i++){
////            for(int j=0; j<unitController.movable(Hero)[i].length; j++){
////                System.out.println(unitController.movable(Hero)[i][j]);
////            }
////        }
//        
//        System.out.println(Arrays.deepToString(unitController.movable(Hero)));
//        
//        
//        
//        
//        
//        
//        
//    }
//    
//    public void testView(){
//    	// the test code
//    	MainMenu bv = new MainMenu();
//    	bv.startGame();
//    }
//    
//    public void testModel(){
////    	
////    	// the test code
////    	// three guardians
////    	Unit behemoth = new Behemoth();
////    	System.out.println(behemoth.toString() + " says hello!.");
////    	behemoth.attack();
////    	System.out.println("");
////    	
////    	Unit hunter = new Hunter();
////    	System.out.println(hunter.toString() + " says hello!.");
////    	hunter.attack();
////    	System.out.println("");
////    	
////    	Unit golem = new Golem();
////    	System.out.println(golem.toString() + " says hello!.");
////    	golem.attack();
////    	System.out.println("");
////    	
////    	// four explorers
////    	Unit scout = new Scout();
////    	System.out.println(scout.toString() + " says hello!.");
////    	scout.useAbility();
////    	System.out.println("");
////    	
////    	Unit trapMaster = new TrapMaster();
////    	System.out.println(trapMaster.toString() + " says hello!.");
////    	trapMaster.useAbility();
////    	System.out.println("");
////    	
////    	Unit hero = new Hero();
////    	System.out.println(hero.toString() + " says hello!.");
////    	hero.useAbility();
////    	System.out.println("");
////    	
////    	Unit tactician = new Tactician();
////    	System.out.println(tactician.toString() + " says hello!.");
////    	tactician.useAbility();
////    	System.out.println("");
////    	
////    	
//   }
//}
