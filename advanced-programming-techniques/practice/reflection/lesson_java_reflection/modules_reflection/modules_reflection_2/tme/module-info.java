// module tsp.module.employee { 
//     opens tsp.module.employee; //to tsp.module.reflection;
//     requires tsp.module.reflection;  
//     exports tsp.module.employee; 
// }

module tme { 
    opens tsp.module.employee; //to tsp.module.reflection;
    requires tmr;  
    exports tsp.module.employee; 
}