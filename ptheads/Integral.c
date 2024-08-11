#include <stdio.h>
#include <mpi.h>
#include <math.h>

double f(double x)
{
    return x*x -4*x + 8;
}

int main()
{
   int    comm_size;               
   int    my_rank;      
   int    n                 = 100000; 
   double local_sum         = 0;
   double global_sum        = 0;
   double a                 = 1.0, 
          b                 = 5.0,
          h                 = (b - a) / n; 
   double local_a; 
   
   MPI_Init(NULL, NULL); 
   MPI_Comm_size(MPI_COMM_WORLD, &comm_size); 
   MPI_Comm_rank(MPI_COMM_WORLD, &my_rank); 

   int local_n = n / comm_size; 
   local_a = a + my_rank * local_n * h;

   for (int i = 0; i < local_n; i++) {
       double x = local_a + i * h;
       local_sum += f(x) * h;
   }

   
    if(my_rank != 0)
    {
        MPI_Send(&local_sum,1,MPI_DOUBLE,0,0,MPI_COMM_WORLD);
        
    } else {
        double x;
        for(int i = 1;i < comm_size;i++)
        {
            MPI_Recv(&x,1,MPI_DOUBLE,i,0,MPI_COMM_WORLD,MPI_STATUS_IGNORE);
            global_sum = global_sum + x;
        }
        global_sum += local_sum;
            
    }

   if (my_rank == 0) {
       printf("Area = %f\n", global_sum);
   }
   
   MPI_Finalize(); 
    
   return 0;
}

