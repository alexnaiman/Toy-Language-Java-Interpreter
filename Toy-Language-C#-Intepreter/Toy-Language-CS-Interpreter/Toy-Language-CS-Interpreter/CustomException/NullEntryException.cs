using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_CS_Interpreter.Model.CustomException
{
    class NullEntryException : Exception
    {
        public NullEntryException(string message) : base(message)
        {
        }
    }
}
