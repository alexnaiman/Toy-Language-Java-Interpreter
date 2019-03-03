using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_CS_Interpreter.Model.Containers
{   
    interface IDictionary<K,V>
    {
        void Put(K key, V value);
        V Get(K key);
        List<V> Values();
        List<K> Keys();
        void Remove(K key);
        IDictionary<K, V> Clone();
    }
}
