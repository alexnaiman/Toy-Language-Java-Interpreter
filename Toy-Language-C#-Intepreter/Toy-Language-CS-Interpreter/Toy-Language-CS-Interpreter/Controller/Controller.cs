using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Toy_Language_CS_Interpreter.Model;
using Toy_Language_CS_Interpreter.Model.Statement;
using Toy_Language_CS_Interpreter.Repository;

namespace Toy_Language_CS_Interpreter.Controller
{
    class Controller
    {
        private IRepository repository;

        public Controller(IRepository repository)
        {
            this.repository = repository;
        }
        public void ExecuteOneStep()
        {
            ProgramState state = repository.GetCurrent();
            try
            {
                IStatement statement = state.ExecutionStack.Pop();
                statement.Execute(state);
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }
        }

        public void ExecuteAllSteps()
        {
            ProgramState state = repository.GetCurrent();
            while (!state.ExecutionStack.IsEmpty())
            {
                ExecuteOneStep();
                repository.LogProgramState();
            }
        }

    }
}
