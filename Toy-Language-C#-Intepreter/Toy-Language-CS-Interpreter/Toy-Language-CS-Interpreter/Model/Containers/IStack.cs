using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_CS_Interpreter.Model.Containers
{
    interface IStack<T>:IEnumerable<T>
    {
        void Push(T element);
        T Pop();
        bool IsEmpty();
    }
}
