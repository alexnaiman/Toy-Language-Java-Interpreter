using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Toy_Language_CS_Interpreter.Controller;
namespace Toy_Language_CS_Interpreter.View
{
    class RunCommand:Command
    {
        private Controller.Controller controller;

        public RunCommand(string key, string description, Controller.Controller controller) : base(key,description)
        {
            this.controller = controller;
        }

        public override void Execute()
        {
            try
            {
                controller.ExecuteAllSteps();
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }
        }
    }
}
