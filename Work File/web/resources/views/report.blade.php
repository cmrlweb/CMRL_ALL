<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>CMRL TVS REPORT</title>
    <link rel="stylesheet" href="style.css" media="all" />
  </head>
  <body>
    <header class="clearfix">
      <div id="logo">
        <img src="logo.png">
      </div>
      <div id="company">
        <h2 class="name">Chennai Metro Rail Limited</h2>
        <div>Admin Building,Poonamallee High Road, Koyambedu,Ch-107. India.</div>
        <div>Phone :  +91 - 44 - 23792000</div>
        <div><a href="mailto:chennaimetrorail@gmail.com">chennaimetrorail@gmail.com</a></div>
      </div>
      </div>
    </header>
    <main>
      <div id="details" class="clearfix">
        <div id="client">
          <div class="to">Operator:</div>
          <h2 class="name">{{$operator}}</h2>
          <div class="address">Last Logged on : Sometime</div>
          <div class="email"><a href="mailto:john@example.com">john@example.com</a></div>
        </div>
        <div id="invoice">
          <h1>{{$assetcode}}</h1>
          <div class="date">REPORT ID : 0001</div>
          <div class="date">Date of Report: 01/06/2015</div>
        </div>
      </div>
      <table border="0" cellspacing="0" cellpadding="0">
        <thead>
          <tr>
            <th class="no">S.no</th>
            <th class="desc">DESCRIPTION</th>
            <th class="unit">Change Done</th>
          </tr>
        </thead>
        <tbody>
          @foreach ($Maintain as $index => $maintain)
          <tr>
            <td class="no">{{$index +1}}</td>
            <td class="desc"><h3>{{$MachineDesc[$index]}}</h3></td>
            <td class="unit">{{$Machvalue[$index]}}</td>
          </tr>
          @endforeach
        </tbody>
      </table>
      <div id="thanks">Thank You</div>
      <div id="notices">
        <div>NOTICE:</div>
        <div class="notice">All Changes are being Forwarded to all the Managers who are in charge for the respective substations.</div>
        <A HREF="javascript:window.print()">Click to Print This Page</A>
      </div>
    </main>
    <footer>
      Report was created on a computer and is valid without the signature and seal.
    </footer>
  </body>
</html>