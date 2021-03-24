Uppdraget i deluppgift A var att implementera så det i applikationen är möjligt att dynamiskt
ändra vilken kommunikatör som används genom att klicka i radioknapparna. Vid
implementation av min lösning skall minst ett vedertaget designmönster användas. Till
kodlösningen skall även en .pdf (detta dokument) lämnas in som besvarar samt motiverar varför
jag valde den aktuella lösningen och vilket eller vilka designmönster jag har använt, samt föra
ett resonemang kring hur min implementation förhåller sig till minst en vedertagen
designprincip.
Nedan mina svar, motivation samt resonemang för Deluppgift A:
Jag gjorde initialt en lösning baserad på designmönstret Abstract Factory Pattern (den som jag
visade dig initialt i skolan före jul). Jag valde det designmönstret för att det verkade vara perfekt
för de flesta vedertagna designprinciperna, så som:
• Single Responsibility Principle (SRP)
Eftersom klassen bara var ansvarig för en sak och det var via Communicator instance
switcha till UDPChatCommunicator eller WebSocketCommunicator. (“A class should
have a single responsibility, where a responsibility is nothing but a reason to change”.)
• Open-Closed Principle (OCP)
Communicator class var en abstract class och det betyder att en abstract class kan
extends från en annan klass eller implementera ett interface (”open for extention but
closed for modification; you should be able to extend a class without modifying it”). Så
man kunde gå och ändra koden men utan att påverka orginalet genom att
implementera en/flera interfaces eller extends en class.
• Liskov Substitutions Principle (LSP)
Det var också perfekt för Liskov Substitution Principle med ”Is – a relationship” (det
hade hjälpt att anropa en super class method och at runtime gjorde polymorphisim
(dynamic binding) istället för att använda if – else statement.
• Interface Segregation Principle (ISP)
Det kändes bra med abstract factory pattern eftersom factory pattern implementerade
bara vad som behövdes. Det betydde att jag tvingade inte på andra classer (onödiga
funktionaliteter) som de (UDP och WebSocket) inte behövde använda. (”Clients should
not be forced to depend on methods they don’t use. Split a larger interface into a
number of smaller interfaces”).
• Dependency Inversion Principle (DIP)
Det kändes som jag inte gjorde brott på DIP eftersom inga högre modules (high-level
modules) var beroende av andra interfaces.
För att lära mig mer och öva mer på designmönster så gjorde jag (efter att jag visat dig min
första lösning, som du sa var OK) om allt från början. Denna lösning (Deluppgift A) som jag nu
skickar in är baserad på designmönstret Singleton Design Pattern.


• Single Responsibility Principle (SRP)
Personligen känns det som CommunicatorGenarator.java gör brott på SRP eftersom
classen responsibility inte returnerar sub type Comunicator object, och inte heller
checkar vilken subtype som ska returneras tillbaka på linje 37. Javisst kunde jag ändra
det efter jag nu när jag skriver detta har upptäckt vad som jag har gjort ”fel (brott), men
jag väljer att behålla det till ditt och mitt nöje så du som lärare ändå kan se att jag lärt
mig och förstått grunderna som du har lärt ut. (Dvs jag vet att jag i denna lösning har
gjort brott mot SRP).


• Open-Closed Principle (OCP)
CommunicatorGenerator.java är helt öppen för extensions men stängd för förändringar
(open for extensions but closed för changes). Men getInstanceOfCommincator method på
linje 37 är hårt knuten ifall kunden kommer att önska att lägga till ett annat alternativ som
DoubleWebSocket, det betyder att det är ”only one reason” att ändra som på lektionen
första veckan gav exempel på. CommunicatorGenerator är öppen för extension men
stängd för ändring.
(“Design classes to be open for extension but closed for modification; you should be able
to extend a class without modifying it. Minimize the need to make changes to existing
classes”).

• Liskov Substitutions Principle (LSP)
Denna (min andra) lösning är tyvärr brott mot LSP eftersom jag istället kunde ha gjort
dynamic binding istället för att ha ”if else” på linje 39-44 (se nedan). Javisst, jag kunde ha
ändrat detta men eftersom jag upptäckte det när jag gick igenom lösningen till denna
rapport så valde jag att behålla det som en insikt att när jag designar på papper (som jag
visade dig i skolan med mitt initiala Classdiagram) så minskar man risken att göra fel med
strategin och refactorering blir enklare:
public class CommuncatorGenerator extends CommunicatorFactory{
public Communicator getInstanceOfCommunicatorType(Communicator
communicatorType){
return communicatorType.getCommunicatorFactory(String chkTxt);
}
 }
 
• Interface Segregation Principle (ISP)
Det skulle ha varit ett brott om jag hade implementerat ett interface och inte skulle
använda implementerat interface-funktionalitet i subclasserna. (”Clients should not be
forced to depend on interface member they do not use”). Det känns som ISP och SRP är
“syskon” dvs dess syfte påminner om varandra.
UPPSALA UNIVERSITET 2020-01-06

Till exempel:
public interface Modem{
 public abstract swithOnModem();
}
public class CommuncatorGenerator Impements Modem{
}
• Dependency Inversion Principle (DIP)
Jag tror inte att min lösning är något brott mot DIP eftersom “Abstractions should not
depend upon details. Details should depend upon abstractions”. I min lösning (del A) är
CommuncatorGenerator inte beroende av low level module eller sånt.

Deluppgift B
Uppdraget i deluppgift B var att implementera detta i applikationen. Till kodlösningen skall även
en .pdf (detta dokument) lämnas in som besvarar samt motiverar vilka vedertagna designprinciper styrks via implementationen av Observer mönstret.
Nedan mina svar, motivation samt resonemang för Deluppgift B:
• Single Responsibility Principle (SRP)
IObserver och IObservable gör inte brott mot SRP eftersom båda Interfacen gör vad som
de verkligen är ansvariga för att göra i applikationen.
• Open-Closed Principle (OCP)
Båda interfacen är öppna för extension men stängda för ändring/modification. Även om
det kommer att komma en nya krav från kunden behöver utvecklaren inte röra IObserver
eller IObservable interface. Ifall det verkligen behövs då kan IObserver och IObservable
extends utan att få huvudvärk men då innebär det att UDPChatCommunicator och
WebSocketCommunicator måste implementera ny/nya functionalitet (självklart kan
Communicator abstract class implementera dessa interface metoder men abstract class
kräver inte att man implementerar interface metoder). Så då blir en anledning att ändra
UDPChatCommunicator och WebSocketCommunicator (implementera nya funktionalitet).
• Liskov Substitutions Principle (LSP)
Vilka klasser som ska implementera IObservable eller IObserver kommer inte bryta
funktionaliteten (applikationen) med en bra strategi. Jag menar att ”from a client’s
perspective, override methods should not break functionality”.
Communicator sub type of IObservable och UDPCommunicator extends Communicator.
Det betyder att ifall jag vill anropa en av IObservable method via UDPCommunicator
instance går det bra. Till exempel IObservable iO = new
UDPCommunicator(MainController chat). Men det finns ingen ”is - a relationship” (is an
IObservable a Cominicator) det betyder för mig att strategin är dåligt uppbyggd från
början.


• Interface Segregation Principle (ISP)
Jag tror att min lösning inte gör brott mot ISP eftersom Communicator class bara har vad
som Communicator och dess subclass/subclasser behöver.

• Dependency Inversion Principle (DIP)
Ifall vi kommer att ändra UdpCommunicator abstract class metoder som extends från
IObservable ska det inte påverka IObservable interface eftersom IObservable high level,
Communicator och UdpCommunicator low level. (”High-level modules simply should not
depend on low-level modules in any way”).
