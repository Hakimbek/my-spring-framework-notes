# Autowiring in Spring
Autowiring feature of spring framework enables you to inject the object dependency implicitly. It internally uses setter or constructor injection.

Autowiring can't be used to inject primitive and string values. It works with reference only.

### Advantage of Autowiring
- It requires the less code because we don't need to write the code to inject the dependency explicitly.

### Disadvantage of Autowiring
- No control of programmer.
- It can't be used for primitive and string values.

## Autowiring Modes

| # |  Mode  | Description                                                          |
| - | ------ | -------------------------------------------------------------------- |
| 1 | no     | It is the default autowiring mode. It means no autowiring bydefault. |
| 2 | byName | The byName mode injects the object dependency according to name of the bean. In such case, property name and bean name must be same. It internally calls setter method. |
| 3 | byType | The byType mode injects the object dependency according to type. So property name and bean name can be different. It internally calls setter method. |
| 4 | constructor | The constructor mode injects the dependency by calling the constructor of the class. It calls the constructor having large number of parameters. |

