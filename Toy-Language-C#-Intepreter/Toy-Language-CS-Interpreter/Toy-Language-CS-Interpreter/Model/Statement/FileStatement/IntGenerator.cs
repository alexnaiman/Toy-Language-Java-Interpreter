using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_CS_Interpreter.Model.Statement.FileStatement
{
    class IntGenerator
    {
        private static int count = 1;
        public static int Generate()
        {
            return count++;
        }
    }
}
