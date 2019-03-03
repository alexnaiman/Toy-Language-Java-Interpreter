using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_CS_Interpreter.Model.Containers
{
    interface IFileTable<K,V>
    {
        void Add(K key, V value);
        void Delete(K key);
        V Get(K key);
        bool Contains(K key);
        List<K> GetAllKeys();
        List<V> GetAllValues();
    }
}
