using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_CS_Interpreter.Model.Statement
{
    interface IStatement
    {
        ProgramState Execute(ProgramState state);
    }
}
