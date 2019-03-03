using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Toy_Language_CS_Interpreter.Model;

namespace Toy_Language_CS_Interpreter.Repository
{
    interface IRepository
    {
        ProgramState GetCurrent();
        void LogProgramState();
    }
}
