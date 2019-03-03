using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Toy_Language_CS_Interpreter.Model;
using Toy_Language_CS_Interpreter.Model.Statement;

namespace Toy_Language_CS_Interpreter.Repository
{
    class Repository : IRepository
    {
        private ProgramState state;

        public Repository(ProgramState state)
        {
            this.state = state;
        }

        public ProgramState GetCurrent()
        {
            return state;
        }

        public void LogProgramState()
        {
            var time = DateTime.Now.ToString("yyyy-MM-dd-hh-mm-ss");
            //var file = File.Create("../../Logs/log_" + time + ".txt");
            using (StreamWriter writer = File.AppendText("../../Logs/log_" + time + ".txt"))
            {
                writer.WriteLine("#################################");
                writer.Write(state);
                }
        }
    }
}
