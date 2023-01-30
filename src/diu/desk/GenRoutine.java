/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diu.desk;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

 
public class GenRoutine {
    
    RoutineData rd= new RoutineData();
    public void RoutineGen() throws IOException{
    RoutineExport r= new RoutineExport();
    r.routineLogic();
    r.RoutinePDF();
    }

    
    public void routineLogic(){
    
            rd.getRoomList();
       rd.getBatchRelatedInfo();
       
       
        
       for(int i=0;i<rd.batchList.size();i++)  //once for eeach batch 
       {
           // finding room number's index in database(room table) for batch i
              int r=rd.getRoomIndex(rd.batchList.get(i))  ;
           
           Queue<String> q = new LinkedList<>();
           

           String batch=rd.batchList.get(i); 
           System.out.println("<<------Batch: "+rd.batchList.get(i)+"------>>");
           System.out.println("Semester: "+rd.semesterList.get(i));
      
           rd.getCourseCodeList(rd.semesterList.get(i));
           
           System.out.println("Course Codes: ");
           for(int j=0;j<rd.courseCodeList.size();j++)
           {
               System.out.println(rd.courseCodeList.get(j));
                
           }
           //-----------------THEORY TIMETABLE GENERATION--------------------//
           
            // <editor-fold defaultstate="collapsed" desc="The batches with 4 theory subjects">
           if(rd.courseCodeList.size()==4)
           {
               //adding 12 periods in the queue
               q.add(rd.courseCodeList.get(0));
               q.add(rd.courseCodeList.get(0));
               q.add(rd.courseCodeList.get(1));
               q.add(rd.courseCodeList.get(1));
               
               q.add(rd.courseCodeList.get(2));
               q.add(rd.courseCodeList.get(2));
               q.add(rd.courseCodeList.get(0));
               q.add(rd.courseCodeList.get(3));
               
               q.add(rd.courseCodeList.get(1));
               q.add(rd.courseCodeList.get(2));
               q.add(rd.courseCodeList.get(3));
               q.add(rd.courseCodeList.get(3));
               
               //i=i'th batch  d=d'th day p=period 
               int d=1, p=1;
               while(q.isEmpty()!=true)
                {
                    if( (rd.room[r][d]!=-1) && (rd.classDay[i][d]!=-1) )   //if the room is available at day 1
                        {

                            rd.period[i][d][p]=q.remove(); p++;
                            rd.period[i][d][p]=q.remove(); p++;
                            rd.period[i][d][p]=q.remove(); p++;
                            rd.period[i][d][p]=q.remove(); p=1;
                            rd.room[r][d]=-1;             //make room r unavailable on day d
                            rd.classDay[i][d]=-1;        //make day d unavailable for batch i
                        }
                             //   System.out.println(Arrays.deepToString(rd.room));  //to print a full arry
                   d++;
                   if(d==8) break;

               }
               
               
               
               
               
           }   // </editor-fold>
           
            // <editor-fold defaultstate="collapsed" desc="The batches with 3 theory subjects">
           else if(rd.courseCodeList.size()==3)
           {
               //for semester 11 & 5 
               if (("11".equals(rd.semesterList.get(i))) || ("5".equals(rd.semesterList.get(i))))
                        {
                           //adding 9 periods in the queue
                        q.add(rd.courseCodeList.get(0));
                        q.add(rd.courseCodeList.get(0));
                        q.add(rd.courseCodeList.get(1));
                        q.add(rd.courseCodeList.get(1));
                        q.add(rd.courseCodeList.get(2));

                        q.add(rd.courseCodeList.get(2));
                        q.add(rd.courseCodeList.get(2));
                        q.add(rd.courseCodeList.get(0));
                        q.add(rd.courseCodeList.get(1));
 
                
                       //if the room is booked in friday by another batch, then move to another classroom   
                       int  d=4, p=1;     //important warning: if friday index changed from 4, make change it here also
                       if( (rd.room[r][4]!=-1) && (rd.classDay[i][4]!=-1) )   //if the room is available at day 4
                                      {

                                          rd.period[i][d][p]=q.remove(); p++;
                                          rd.period[i][d][p]=q.remove(); p++;
                                          rd.period[i][d][p]=q.remove(); p++;
                                          rd.period[i][d][p]=q.remove(); p++;
                                          rd.period[i][d][p]=q.remove();
                                          rd.room[r][d]=-1;             //make room r unavailable on day d
                                          rd.classDay[i][d]=-1;        //make day d unavailable for batch i
                                      }
                       else
                       { 
                           int next=r+1;
                           while(true)
                                {
                                     if( (rd.room[next][4]!=-1) && (rd.classDay[i][4]!=-1) )   //if the next room is available at day 4
                                      {                                                     //important warning: if friday index changed from 4, make change it here also

                                          rd.period[i][d][p]=q.remove(); p++;
                                          rd.period[i][d][p]=q.remove(); p++;
                                          rd.period[i][d][p]=q.remove(); p++;
                                          rd.period[i][d][p]=q.remove(); p++;
                                          rd.period[i][d][p]=q.remove();
                                          rd.room[next][d]=-1;             //make room r unavailable on day d
                                          rd.classDay[i][d]=-1;        //make day d unavailable for batch i
                                          rd.secondRoom[i][d]="Room-"+rd.roomList.get(next-1);
                                         System.out.println("A room is altered to another room for better fit");

                                          break;
                                      }
                                    
                                     next++;
                                     

                                }
                       }   

                        //i=i'th batch,  d=d'th day, p=period 
                        // d1=Tue d2=Wed d3=Thu d4=fri d5=Sat d6=sun
                        d=1; p=1;
                             while(q.isEmpty()!=true)
                              {
                                  if( (rd.room[r][d]!=-1) && (rd.classDay[i][d]!=-1) )   //if the room is available at day 1
                                      {

                                          rd.period[i][d][p]=q.remove(); p++;
                                          rd.period[i][d][p]=q.remove(); p++;
                                          rd.period[i][d][p]=q.remove(); p++;
                                          rd.period[i][d][p]=q.remove(); p=1;
                                          rd.room[r][d]=-1;             //make room r unavailable on day d
                                          rd.classDay[i][d]=-1;        //make day d unavailable for batch i
                                      }
                                           //   System.out.println(Arrays.deepToString(rd.room));  //to print a full arry
                                 d++;
                                 if(d==8) break;
                             }    

                        } // end for sem- 11 & 5
                          

   // for semester 10 
           else  if ("10".equals(rd.semesterList.get(i)))
                        {
                           //adding 9 periods in the queue
                        q.add(rd.courseCodeList.get(0));
                        q.add(rd.courseCodeList.get(0));
                        q.add(rd.courseCodeList.get(1));
                        q.add(rd.courseCodeList.get(1));
                        
                   //     q.add(rd.courseCodeList.get(2));  // important warning: will be added with Lab-1 

                        q.add(rd.courseCodeList.get(2));
                        q.add(rd.courseCodeList.get(2));
                        q.add(rd.courseCodeList.get(0));
                        q.add(rd.courseCodeList.get(1));


                        //i=i'th batch,  d=d'th day, p=period 
                        // d1=Tue d2=Wed d3=Thu d4=fri d5=Sat d6=sun
                      int  d=1, p=1;
                             while(q.isEmpty()!=true)
                              {
                                  if( (rd.room[r][d]!=-1) && (rd.classDay[i][d]!=-1) )   //if the room is available at day 1
                                      {

                                          rd.period[i][d][p]=q.remove(); p++;
                                          rd.period[i][d][p]=q.remove(); p++;
                                          rd.period[i][d][p]=q.remove(); p++;
                                          rd.period[i][d][p]=q.remove(); p=1;
                                          rd.room[r][d]=-1;             //make room r unavailable on day d
                                          rd.classDay[i][d]=-1;        //make day d unavailable for batch i
                                      }
                                           //   System.out.println(Arrays.deepToString(rd.room));  //to print a full arry
                                 d++;
                                 if(d==8) break;
                             }    

                        } //end for sem- 10        

                          // for semester 10 
           else  if ("8".equals(rd.semesterList.get(i)))
                        {
                            int  d=1, p=1;
                           //adding 9 periods in the queue
                        q.add(rd.courseCodeList.get(0));
                        q.add(rd.courseCodeList.get(0));
                        q.add(rd.courseCodeList.get(1));
                        q.add(rd.courseCodeList.get(1));
                        
                        //i=i'th batch,  d=d'th day, p=period 
                        // d1=Tue d2=Wed d3=Thu d4=fri d5=Sat d6=sun
                      
                             while(q.isEmpty()!=true)
                              {
                                  if( (rd.room[r][d]!=-1) && (rd.classDay[i][d]!=-1) )   //if the room is available at day 1
                                      {

                                          rd.period[i][d][p]=q.remove(); p++;
                                          rd.period[i][d][p]=q.remove(); p++;
                                          rd.period[i][d][p]=q.remove(); p++;
                                          rd.period[i][d][p]=q.remove(); p=1;
                                          rd.room[r][d]=-1;             //make room r unavailable on day d
                                          rd.classDay[i][d]=-1;        //make day d unavailable for batch i
                                      }
                                           //   System.out.println(Arrays.deepToString(rd.room));  //to print a full arry
                                 d++;
                                 if(d==8) break;
                             }   

                        q.add(rd.courseCodeList.get(0));
                        q.add(rd.courseCodeList.get(1));
                        q.add(rd.courseCodeList.get(2));  
                        
                        //i=i'th batch,  d=d'th day, p=period 
                        // d1=Tue d2=Wed d3=Thu d4=fri d5=Sat d6=sun
                       d=1;
                             while(q.isEmpty()!=true)
                              {
                                  if( (rd.room[r][d]!=-1) && (rd.classDay[i][d]!=-1) )   //if the room is available at day 1
                                      {
                                          rd.period[i][d][p]=q.remove(); p++;
                                          rd.period[i][d][p]=q.remove(); p++;
                                          rd.period[i][d][p]=q.remove(); p=1;
                                          rd.room[r][d]=-1;             //make room r unavailable on day d
                                          rd.classDay[i][d]=-1;        //make day d unavailable for batch i
                                      }
                                           //   System.out.println(Arrays.deepToString(rd.room));  //to print a full arry
                                 d++;
                                 if(d==8) break;
                             }   
                        
                        q.add(rd.courseCodeList.get(2));
                        q.add(rd.courseCodeList.get(2));                        

                        //i=i'th batch,  d=d'th day, p=period 
                        // d1=Tue d2=Wed d3=Thu d4=fri d5=Sat d6=sun
                       d=1;
                             while(q.isEmpty()!=true)
                              {
                                  if( (rd.room[r][d]!=-1) && (rd.classDay[i][d]!=-1) )   //if the room is available at day 1
                                      {

                                          rd.period[i][d][p]=q.remove(); p++;
                                          rd.period[i][d][p]=q.remove(); p=1;
                                          rd.room[r][d]=-1;             //make room r unavailable on day d
                                          rd.classDay[i][d]=-1;        //make day d unavailable for batch i
                                      }
                                           //   System.out.println(Arrays.deepToString(rd.room));  //to print a full arry
                                 d++;
                                 if(d==8) break;
                             }    

                        } //end for sem- 8                  
               
           } //end of course code size=3
            // </editor-fold>
           
            // <editor-fold defaultstate="collapsed" desc="The batches with 1 theory subject, 12th semester">
           else if(rd.courseCodeList.size()==1)
           {
               //adding 3 periods in the queue
               q.add(rd.courseCodeList.get(0));
               q.add(rd.courseCodeList.get(0));
               q.add(rd.courseCodeList.get(0));

               //i=i'th batch,  d=d'th day, p=period 
               int d=1, p=1;
                    while(q.isEmpty()!=true)
                     {
                         if( (rd.room[r][d]!=-1) && (rd.classDay[i][d]!=-1) )  //if the room is available at day 1
                             {

                                 rd.period[i][d][p]=q.remove(); p++;
                                 rd.period[i][d][p]=q.remove(); p++;
                                 rd.period[i][d][p]=q.remove(); p=1;
                                 rd.room[r][d]=-1;             //make room r unavailable on day d
                                 rd.classDay[i][d]=-1;        //make day d unavailable for batch i
                             }
                                  //   System.out.println(Arrays.deepToString(rd.room));  //to print a full arry
                        d++;
                        if(d==8) break;
                    }       
           }
            // </editor-fold>
           
            
            
          //-----------------LAB TIMETABLE GENERATION--------------------// 
           // <editor-fold defaultstate="collapsed" desc="LAB TIMETABLE GENERATION">
          
           if(rd.labCodeList.size()==1)
            {
               //adding 2 periods in the queue
               q.add(rd.labCodeList.get(0));
               q.add(rd.labCodeList.get(0));
       
               //i=i'th batch,  d=d'th day, p=period 
               int d=1, p=1, index=1;
                    while(q.isEmpty()!=true) 
                    {
                        if(rd.classDay[i][d]!=-1)
                        {
                            for(index=1;index<8;index++)
                            {
                                if(rd.labroom[index][d]!=-1)  //if the room is available at day 1
                                    {
                                        rd.period[i][d][p]=q.remove()+"\nLab-"+index;  p++;
                                        rd.period[i][d][p]=q.remove()+"\nLab-"+index; p=1;
                                        rd.labroom[index][d]=-1;             //make room r unavailable on day d
                                        rd.classDay[i][d]=-1;        //make day d unavailable for batch i
                                        break;
                                    }
                            }
                        }
                        d++;
                        System.out.println("Day-"+d+" Lab-"+index);
                     if(d==8) break;
                    }       
           }  
           
           
           
           //For 2 lab Subjects
           else if(rd.labCodeList.size()==2)
            {
               //adding 2 periods in the queue
               q.add(rd.labCodeList.get(0));
               q.add(rd.labCodeList.get(0));
               q.add(rd.labCodeList.get(1));
               q.add(rd.labCodeList.get(1));
       
               //i=i'th batch,  d=d'th day, p=period 
               int d=1, p=1, index=1;
                    while(q.isEmpty()!=true) 
                    {
                        if(rd.classDay[i][d]!=-1)
                        {
                            for(index=1;index<8;index++)
                            {
                                if(rd.labroom[index][d]!=-1)  //if the room is available at day 1
                                    {
                                        rd.period[i][d][p]=q.remove()+"\nLab-"+index;  p++;
                                        rd.period[i][d][p]=q.remove()+"\nLab-"+index;  p++;
                                        rd.period[i][d][p]=q.remove()+"\nLab-"+index;  p++;
                                        rd.period[i][d][p]=q.remove()+"\nLab-"+index;  p=1; 
                                        rd.labroom[index][d]=-1;             //make room r unavailable on day d
                                        rd.classDay[i][d]=-1;        //make day d unavailable for batch i
                                        break;
                                    }
                            }
                        }
                        d++;
                        System.out.println("Day-"+d+" Lab-"+index);
                     if(d==8) break;
                    }       
           }  
           
           
           
           
           
            // </editor-fold>
          
         
        
      
       
         
        
               
        System.out.println(Arrays.deepToString(rd.room));  //to print a full arry
        
    
    }
}

}