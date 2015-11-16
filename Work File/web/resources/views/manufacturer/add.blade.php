@extends('app')

@section('content')

{!! Form::open(array('url' => '/manufacturer/add', 'class' => 'form-horizontal')) !!}
	
	<div class="form-group">
		<div class="control-label col-sm-2">
			{!!Form::label('mnlabel','Manufacturer id: ')!!}
		</div>
    	<div class="col-sm-2">
      		{!!Form::text('Mid',$lastmanu->Mid+1)!!}
    	</div>
    	<div class="col-sm-4">
    		{!!Form::label('mnwarning','Warning! Dont Change Value Until You are Sure.')!!}
    	</div>
	</div>

	<div class="form-group">
		<div class="control-label col-sm-2">
			{!!Form::label('namelabel','Name: ')!!}
		</div>
    	<div class="col-sm-10">
      		{!!Form::text('Name')!!}
      		
    	</div>
	</div>

	<div class="form-group">
		<div class="control-label col-sm-2">
			{!!Form::label('emaillabel','Email: ')!!}
		</div>
    	<div class="col-sm-10">
      		{!!Form::text('email')!!}
      		
    	</div>
	</div>

	<div class="form-group">
		<div class="control-label col-sm-2">
			{!!Form::label('ctnolabel','Contact No: ')!!}
		</div>
    	<div class="col-sm-10">
      		{!!Form::text('ctno')!!}
      		
    	</div>
	</div>

	<div class="form-group">
		<div class="control-label col-sm-2">
			{!!Form::label('ctnamelabel','Contact Name: ')!!}
		</div>
    	<div class="col-sm-10">
      		{!!Form::text('ctname')!!}
      		
    	</div>
	</div>

	<div class="form-group">
		<div class="control-label col-sm-2">
			{!!Form::label('ctno2label','Contact No: ')!!}
		</div>
    	<div class="col-sm-2">
      		{!!Form::text('ctno2')!!}
      		
    	</div>
    	<div class="col-sm-2">
    		{!!Form::label('mnwarning2','*Optional')!!}
    	</div>
	</div>

	<div class="form-group">
		<div class="control-label col-sm-2">
			{!!Form::label('ctname2label','Contact Name: ')!!}
		</div>
    	<div class="col-sm-2">
      		{!!Form::text('ctname2')!!}
      		
    	</div>
    	<div class="col-sm-2">
    		{!!Form::label('mnwarning2','*Optional')!!}
    	</div>
	</div>

	<div class="form-group">
		<div class="control-label col-sm-2">
			{!!Form::label('preequip','Pre Existing Equipments:')!!}
		</div>
    	<div class="col-sm-10">
      		<select id="selector" name="selector" class="form-control" multiple="multiple">
      			@foreach($Enames as $index => $equipment)
      			<option value="{{$index + 1}}">{{$equipment->Name}}</option>
      			@endforeach
      		</select>
    	</div>
	</div>

	<div class="form-group">
		<div class="control-label col-sm-2">
			{!!Form::label('pnamelabel','Product name: ')!!}
		</div>
    	<div class="col-sm-10">
      		{!!Form::text('Pname')!!}
      		
    	</div>
	</div>

  <div class="form-group">
    <div class="col-sm-2"></div>
    <div class="col-sm-10">
      <input type="hidden" name="_token" value="{{ csrf_token() }}">
      <input type="submit" value="Submit">
    </div>
  </div>
{!!Form::close()!!}
@endsection