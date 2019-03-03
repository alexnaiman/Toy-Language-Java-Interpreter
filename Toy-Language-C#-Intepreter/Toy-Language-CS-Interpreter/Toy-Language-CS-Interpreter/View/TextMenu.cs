using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_CS_Interpreter.View
{
    class TextMenu
    {
        private Dictionary<string, Command> commands;

        public TextMenu()
        {
            commands = new Dictionary<string, Command>();
        }

        public void AddCommand(Command c)
        {
            commands.Add(c.GetKey(), c);
        }

        private void PrintMenu()
        {
            foreach (Command com in commands.Values)
            {
                string line = string.Format("{0}: {1}", com.GetKey(), com.GetDescription());
                Console.WriteLine(line);
            }
        }

        public void Show()
        {
            while (true)
            {
                PrintMenu();
                Console.WriteLine("Input the option: ");
                string command = Console.ReadLine();
                if (!commands.ContainsKey(command))
                {
                    Console.WriteLine("I dont understan you");
                    continue;
                }
                Command c = commands[command];
                c.Execute();
            }
        }
    }
}
