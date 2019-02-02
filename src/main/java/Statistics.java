import jsat.ARFFLoader;
import jsat.DataSet;
import jsat.classifiers.DataPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Statistics {

    public class linearRegression
    {

        public double x;
        public double y;
        public double xDiff;
        public double xDiffSqr;
        public double yDiff;
        public double xyDiff;

        linearRegression(double x, double y)
        {
            this.x=x;
            this.y=y;
        }
        void count(double xMean, double yMean)
        {
            xDiff=x-xMean;
            yDiff=y-yMean;
            xDiffSqr=xDiff*xDiff;
            xyDiff=xDiff*yDiff;
        }

    }

    public class średniaKrocząca
    {
        średniaKrocząca(double x, double y)
        {
            this.x=x;
            this.y=y;
        }
        public double x;
        public double y;
        public double diff;
    }
   int[] table = {2, 3, 4, 6, 7, 10, 11, 13, 15, 17, 21, 22, 23, 24, 25, 27, 28, 30, 31, 34, 38, 40, 42, 43, 44, 45, 46, 48, 49, 54, 56, 57, 59, 60, 61, 64, 66, 67, 69, 73, 75, 76, 77, 78, 79, 88, 89, 93, 95, 98, 99, 100, 102, 103, 104, 107, 108, 112, 113, 114, 117, 121, 123, 125, 126, 127, 128, 129, 131, 132, 133, 134, 136, 142, 143, 145, 146, 148, 152, 154, 158, 161, 162, 163, 164, 165, 166, 168, 174, 178, 179, 181, 183, 187, 188, 189, 190, 192, 194, 195, 197, 198, 200, 205, 207, 211, 213, 214, 216, 218, 220, 223, 224, 226, 227, 228, 229, 230, 233, 234, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 251, 253, 258, 266, 267, 268, 273, 274, 277, 280, 284, 285, 286, 288, 289, 291, 293, 295, 296, 299, 300, 305, 309, 310, 311, 316, 317, 318, 320, 322, 324, 326, 330, 332, 333, 334, 335, 336, 339, 342, 344, 345, 346, 347, 348, 353, 354, 357, 359, 360, 363, 364, 367, 368, 371, 375, 376, 379, 382, 384, 389, 392, 397, 398, 399, 404, 405, 409, 411, 413, 414, 417, 418, 419, 420, 422, 426, 428, 429, 432, 433, 434, 436, 437, 438, 439, 440, 441, 442, 443, 446, 452, 454, 455, 456, 459, 461, 464, 466, 467, 468, 471, 473, 476, 478, 479, 480, 482, 488, 489, 490, 492, 493, 495, 499, 502, 504, 506, 507, 509, 511, 513, 514, 515, 516, 517, 518, 519, 520, 521, 524, 525, 526, 529, 530, 531, 532, 535, 536, 538, 541, 542, 543, 544, 545, 546, 547, 549, 550, 553, 554, 555, 556, 557, 565, 570, 571, 572, 573, 575, 576, 577, 581, 582, 583, 586, 587, 588, 592, 594, 596, 597, 600, 602, 604, 605, 606, 611, 613, 614, 618, 619, 621, 622, 623, 626, 627, 628, 629, 630, 634, 636, 637, 638, 640, 650, 653, 654, 655, 659, 660, 662, 666, 667, 670, 673, 675, 676, 678, 683, 685, 687, 688, 692, 693, 695, 697, 698, 699, 700, 702, 703, 704, 705, 706, 707, 710, 711, 715, 718, 719, 721, 725, 726, 728, 729, 730, 732, 733, 734, 735, 736, 737, 739, 740, 744, 745, 746, 747, 750, 751, 752, 755, 757, 759, 761, 763, 765, 768, 774, 780, 781, 784, 785, 787, 791, 792, 793, 794, 801, 805, 807, 808, 809, 810, 813, 815, 817, 818, 819, 822, 824, 827, 828, 832, 836, 837, 838, 839, 840, 842, 843, 844, 846, 848, 851, 854, 857, 858, 859, 865, 867, 868, 869, 871, 872, 876, 877, 880, 881, 882, 885, 886, 888, 889, 890, 893, 898, 899, 900, 903, 909, 910, 915, 917, 920, 921, 922, 924, 925, 926, 927, 928, 930, 931, 932, 937, 938, 939, 941, 943, 944, 946, 948, 949, 951, 952, 953, 954, 956, 957, 958, 960, 961, 964, 966, 967, 970, 971, 972, 974, 975, 980, 982, 985, 987, 988, 989, 990};

   public List<linearRegression> dataList =new ArrayList();
   public List<średniaKrocząca> dataList2 = new ArrayList();

    double a;
    double b;
    double last;
   public void addData(int methodId, double x, double y)
   {
        if(methodId==1)
        {
            dataList.add(new linearRegression(x,y));
        }
       if(methodId==2)
       {
           dataList2.add(new średniaKrocząca(x,y));
       }

   }

   void count(int methodId)
   {
       if(methodId==1)
       {
           double xMean;
           double xMeanSum=0;
           double yMean;
           double yMeanSum=0;
           double xyDiffSum=0;
           double xSquaredDiffSum=0;
           for(linearRegression a: dataList)
           {
                xMeanSum+=a.x;
                yMeanSum+=a.y;
           }
           xMean=xMeanSum/dataList.size();
           yMean=yMeanSum/dataList.size();
           for(linearRegression a: dataList)
           {
               a.count(xMean,yMean);
               xyDiffSum+=a.xyDiff;
               xSquaredDiffSum+=a.xDiffSqr;

           }
           a=xyDiffSum/xSquaredDiffSum;
           b=yMean-(a*xMean);

       }

       if(methodId==2)
       {
           double mean=0;
           double sum=0;
           for(średniaKrocząca a: dataList2)
               {
                   mean+=a.y;
               }

           for(średniaKrocząca a: dataList2)
           {
               a.diff=(a.y-mean)*(a.y-mean);
               sum+=a.diff;
           }

           double stDev = Math.sqrt(sum/(dataList2.size()-1));

           Random r = new Random();
            last = mean-stDev + r.nextDouble() + r.nextInt()%(Math.ceil((2*stDev)));
       }
   }

   public double finalCount(double x, int methodId)
   {
       count(methodId);
       if(methodId==1)
       {
           return (a*x + b);
       }
       else
       {
            return last;
       }
   }


}
