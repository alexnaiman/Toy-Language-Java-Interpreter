using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_CS_Interpreter.Model.Containers
{
    interface IList<T>:IEnumerable<T>
    {
        void Add(T element);
        T Get(int index);
        void Remove(int index);
        int Size();
        //List<T> toList
    }
}
