using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_CS_Interpreter.Model.Containers
{
    class CustomStack<T> : IStack<T>
    {
        private Stack<T> stack;

        public CustomStack()
        {
            stack = new Stack<T>();
        }

        public IEnumerator<T> GetEnumerator()
        {
            return stack.GetEnumerator();
        }

        public bool IsEmpty()
        {
            return stack.Count == 0;
        }

        public T Pop()
        {
            return stack.Pop();
        }

        public void Push(T element)
        {
            stack.Push(element);
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return stack.GetEnumerator();
        }

        public override string ToString()
        {
            StringBuilder buffer = new StringBuilder();

            foreach (T element in stack)
            {
                buffer.Append(element);
                buffer.Append(", ");
            }
            buffer.Append("\r\n");
            return buffer.ToString();
        }
    }
}
