using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_CS_Interpreter.CustomException
{
    class FileException : Exception
    {
        public FileException(string message) : base(message)
        {
        }
    }
}
